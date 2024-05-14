package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.SystemRoleDTO;
import org.talend.esbconsole.server.domain.api.model.SystemUserDTO;
import org.talend.esbconsole.server.domain.api.model.UserRolesDTO;
import org.talend.esbconsole.server.domain.api.param.AssignRoleParam;
import org.talend.esbconsole.server.domain.api.param.ResetPasswordParam;
import org.talend.esbconsole.server.domain.api.param.UserCreateParam;
import org.talend.esbconsole.server.domain.api.param.UserModifyParam;
import org.talend.esbconsole.server.domain.api.param.UserPageQueryParam;
import org.talend.esbconsole.server.domain.api.query.UserPageQuery;
import org.talend.esbconsole.server.domain.api.service.SystemUserService;
import org.talend.esbconsole.server.domain.core.converter.SystemRoleConverter;
import org.talend.esbconsole.server.domain.core.converter.SystemUserConverter;
import org.talend.esbconsole.server.domain.core.converter.query.UserQueryConverter;
import org.talend.esbconsole.server.domain.core.util.GuavaCacheUtil;
import org.talend.esbconsole.server.domain.repository.entity.SystemRoleDO;
import org.talend.esbconsole.server.domain.repository.entity.SystemUserDO;
import org.talend.esbconsole.server.domain.repository.entity.SystemUserRoleDO;
import org.talend.esbconsole.server.domain.repository.mapper.SystemRoleDAO;
import org.talend.esbconsole.server.domain.repository.mapper.SystemUserDAO;
import org.talend.esbconsole.server.domain.repository.mapper.SystemUserRoleDAO;
import org.talend.esbconsole.server.tools.base.exception.authentication.PasswordErrorException;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.tools.common.utils.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 用户相关的功能服务接口实现类
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SystemRoleDAO systemRoleDAO;

    @Autowired
    private SystemUserDAO systemUserDAO;

    @Autowired
    private SystemUserRoleDAO systemUserRoleDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GuavaCacheUtil guavaCacheUtil;

    @Autowired
    private SystemUserConverter systemUserConverter;

    @Autowired
    private UserQueryConverter userQueryConverter;

    @Autowired
    private SystemRoleConverter systemRoleConverter;

    @Override
    public void addUser(UserCreateParam userCreateParam) {
        SystemUserDO systemUserDO = systemUserConverter.param2do(userCreateParam);
        systemUserDO.setId(UUIDUtil.getUUID());
        // 密码加密
        String encodedPassword = passwordEncoder.encode(userCreateParam.getPassword());
        systemUserDO.setPassword(encodedPassword);
        systemUserDO.setCreateTime(LocalDateTime.now());
        systemUserDO.setUpdateTime(LocalDateTime.now());
        systemUserDAO.saveSystemUser(systemUserDO);
    }

    @Override
    @Transactional
    public void removeUser(String id) {
        // 删除用户在用户角色表的信息
        deleteAuthorityByUserId(id);
        systemUserDAO.removeSystemUser(id);
    }

    @Override
    public void modifyUser(UserModifyParam userModifyParam) {
        SystemUserDO systemUserDO = systemUserConverter.param2do(userModifyParam);
        systemUserDO.setUpdateTime(LocalDateTime.now());
        systemUserDAO.updateSystemUser(systemUserDO);
    }

    @Override
    public void disableUser(String id) {
        SystemUserDO user = SystemUserDO
                .builder()
                .id(id)
                .status("0")
                .updateTime(LocalDateTime.now())
                .build();
        systemUserDAO.updateSystemUser(user);
    }

    @Override
    public void enableUser(String id) {
        SystemUserDO user = SystemUserDO
                .builder()
                .id(id)
                .status("1")
                .updateTime(LocalDateTime.now())
                .build();
        systemUserDAO.updateSystemUser(user);
    }

    @Override
    public List<SystemUserDTO> getAllUsers() {
        List<SystemUserDO> systemUserDOS = systemUserDAO.listAllSystemUsers();
        List<SystemUserDTO> systemUserDTOS = systemUserConverter.do2dto(systemUserDOS);
        return systemUserDTOS;
    }

    @Override
    public PageResult<SystemUserDTO> getUsers(UserPageQueryParam userPageQueryParam) {
        PageHelper.startPage(userPageQueryParam.getPageNum(), userPageQueryParam.getPageSize());
        UserPageQuery userPageQuery = userQueryConverter.param2query(userPageQueryParam);
        List<SystemUserDO> systemUserDOS = systemUserDAO.listAllSystemUsersByConditions(userPageQuery);
        PageInfo<SystemUserDO> pageInfo = new PageInfo<SystemUserDO>(systemUserDOS);
        List<SystemUserDTO> systemUserDTOS = systemUserConverter.do2dto(systemUserDOS);
        return PageResult.of(systemUserDTOS, pageInfo.getTotal(), (long) pageInfo.getPageNum(), (long) pageInfo.getPageSize());
    }

    @Override
    public SystemUserDTO getUser(String id) {
        SystemUserDO systemUserDO = systemUserDAO.getSystemUserById(id);
        SystemUserDTO systemUserDTO = systemUserConverter.do2dto(systemUserDO);
        return systemUserDTO;
    }

    @Override
    public SystemUserDTO getUserByUserName(String userName) {
        SystemUserDO systemUserDO = systemUserDAO.getSystemUserByUserName(userName);
        SystemUserDTO systemUserDTO = systemUserConverter.do2dto(systemUserDO);
        return systemUserDTO;
    }

    @Override
    public void resetPassword(ResetPasswordParam resetPasswordParam) {
        SystemUserDO systemUserDO = systemUserConverter.param2do(resetPasswordParam);
        systemUserDO.setPassword(passwordEncoder.encode(resetPasswordParam.getPassword()));
        systemUserDO.setUpdateTime(LocalDateTime.now());
        // 密码加密
        systemUserDAO.updateSystemUser(systemUserDO);
        // 删除缓存
        guavaCacheUtil.removeUserDetails(systemUserDO.getId());
    }

    @Override
    @Transactional
    public void assignRoleForUser(AssignRoleParam assignRoleParam) {
        LocalDateTime now = LocalDateTime.now();
        String userId = assignRoleParam.getUserId();
        // 用户的角色的id的集合
        Set<String> roleIdList = assignRoleParam.getRoleIdList();
        List<SystemUserRoleDO> userRoleList = new ArrayList<>();
        for (String roleId : roleIdList) {
            userRoleList.add(new SystemUserRoleDO(UUIDUtil.getUUID(), userId, roleId, now, now));
        }
        // 删除角色相应的表信息，即清空角色
        systemUserRoleDAO.removeSystemUserRoleByUserId(userId);
        // 插入信息，设置角色
        if (roleIdList.size() > 0) {
            systemUserRoleDAO.saveSystemUserRoleList(userRoleList);
        }
    }

    @Override
    public UserRolesDTO getAllRolesAndUserRoles(String id) {
        List<SystemRoleDO> systemRoleDOS = systemRoleDAO.listAllSystemRoles();
        List<SystemRoleDTO> systemRoleDTOS = systemRoleConverter.do2dto(systemRoleDOS);
        List<String> roleIdList = systemUserRoleDAO.listRoleIdByUserId(id);
        return new UserRolesDTO(systemRoleDTOS, roleIdList);
    }

    @Override
    public void deleteAuthorityByUserId(String id) {
        systemUserRoleDAO.removeSystemUserRoleByUserId(id);
    }

    @Override
    public void verifyPassword(String id, String password) {
        String encodedPassword = systemUserDAO.getPasswordById(id);
        if (!passwordEncoder.matches(password, encodedPassword)) {
            throw new PasswordErrorException("密码错误");
        }
    }

    @Override
    public void changePassword(String id, String password) {
        SystemUserDO systemUserDO = new SystemUserDO();
        systemUserDO.setId(id);
        systemUserDO.setPassword(passwordEncoder.encode(password));
        systemUserDO.setUpdateTime(LocalDateTime.now());
        // 密码加密
        systemUserDAO.updateSystemUser(systemUserDO);
        // 删除缓存
        guavaCacheUtil.removeUserDetails(systemUserDO.getId());
    }
}
