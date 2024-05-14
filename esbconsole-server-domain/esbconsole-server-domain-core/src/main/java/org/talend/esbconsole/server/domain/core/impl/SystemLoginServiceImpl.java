package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.MetaDTO;
import org.talend.esbconsole.server.domain.api.model.RouteModel;
import org.talend.esbconsole.server.domain.api.model.RouterDTO;
import org.talend.esbconsole.server.domain.api.model.UserInfoDTO;
import org.talend.esbconsole.server.domain.api.param.LoginUserParam;
import org.talend.esbconsole.server.domain.api.service.RouterService;
import org.talend.esbconsole.server.domain.api.service.SystemLoginService;
import org.talend.esbconsole.server.domain.core.util.GuavaCacheUtil;
import org.talend.esbconsole.server.domain.core.util.LoginUserDetails;
import org.talend.esbconsole.server.domain.repository.mapper.SystemAuthorityDAO;
import org.talend.esbconsole.server.domain.repository.mapper.SystemUserDAO;
import org.talend.esbconsole.server.tools.common.utils.JWTUtil;
import org.talend.esbconsole.server.tools.common.utils.StringUtil;
import org.talend.esbconsole.server.tools.common.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 用户相关功能服务接口 {@link SystemLoginService} 的实现类
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Service
public class SystemLoginServiceImpl implements SystemLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private GuavaCacheUtil guavaCacheUtil;

    @Autowired
    private RouterService routerService;

    @Autowired
    private SystemAuthorityDAO systemAuthorityDAO;

    @Autowired
    private SystemUserDAO systemUserDAO;

    /**
     * 处理登录逻辑
     *
     * @param loginUserParam
     * @return
     */
    @Override
    public String login(LoginUserParam loginUserParam) {
        // AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserParam.getUserName(), loginUserParam.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 如果认证没通过，给出对应的提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }
        // 如果认证通过了，使用userid生成一个jwt jwt存入ResponseResult返回
        LoginUserDetails loginUserDetails = (LoginUserDetails) authenticate.getPrincipal();
        String userid = loginUserDetails.getSystemUser().getId();
        String cacheId = userid + "+" + UUIDUtil.getUUID();
        String jwt = JWTUtil.createJWT(cacheId);
        // 把完整的用户信息存入本地缓存Guava Cache userid作为key
        guavaCacheUtil.setUserDetails(cacheId, loginUserDetails);
        return jwt;
    }

    @Override
    public void logout(String userId) {
        guavaCacheUtil.removeUserDetails(userId);
    }

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param userId
     * @return
     */
    @Override
    public List<RouterDTO> getRouter(String userId) {
        List<RouteModel> treeStruct = routerService.getRouteListToTreeByUserId(userId);
        // 三、根据树形结构的菜单列表构建前端路由
        List<RouterDTO> routers = buildRouter(treeStruct);
        return routers;
    }

    @Override
    public UserInfoDTO getUserInfo(String userId) {
        String userName = systemUserDAO.getUserNameById(userId);
        Set<String> systemAuthorityList = systemAuthorityDAO.listAuthorityKeyByUserId(userId);
        return new UserInfoDTO(userName, systemAuthorityList);
    }

    /**
     * 构建前端路由所需要的路由表（菜单）
     *
     * @param treeStruct 路由（菜单）列表
     * @return 路由列表
     */
    @Override
    public List<RouterDTO> buildRouter(List<RouteModel> treeStruct) {
        List<RouterDTO> routers = new LinkedList<RouterDTO>();
        for (RouteModel node : treeStruct) {
            RouterDTO router = new RouterDTO();
            router.setHidden(false);
            router.setName(node.getRouteName());
            router.setMenuName(node.getName());
            router.setOrderNumber(node.getOrderNum());
            // router.setPath(getRouterPath(node));
            router.setPath(node.getRoutePath());
            router.setComponent(node.getRouteComponent());
            router.setQuery("");
            router.setMeta(new MetaDTO(node.getName(), node.getRouteLevel(), false, node.getIcon()));
            List<RouteModel> childrenMenus = node.getChildren();
            if (StringUtil.isNotEmpty(childrenMenus)) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildRouter(childrenMenus));
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 获取路由地址
     *
     * @param router 路由（菜单）信息
     * @return 路由地址
     */
    public String getRouterPath(RouteModel router) {
        String routerPath = router.getRoutePath();
        // 是一级目录
        if (0 == router.getParentId().intValue()) {
            routerPath = "/" + router.getRoutePath();
        }
        return routerPath;
    }

}
