package org.talend.esbconsole.server.domain.api.service;

import org.talend.esbconsole.server.domain.api.model.RouteModel;
import org.talend.esbconsole.server.domain.api.model.RouterDTO;
import org.talend.esbconsole.server.domain.api.model.UserInfoDTO;
import org.talend.esbconsole.server.domain.api.param.LoginUserParam;

import java.util.List;

/**
 * 用户相关功能服务接口
 *
 * @author xingzilong
 * @date 2023/05/04
 */
public interface SystemLoginService {
    /**
     * 处理登录逻辑
     *
     * @param loginUserParam 用户的登录信息包含用户名、密码等信息
     * @return
     */
    String login(LoginUserParam loginUserParam);

    /**
     * 退出登录
     *
     * @param userId 退出登录的用户de id
     * @return
     */
    void logout(String userId);

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param userId 用户ID
     * @return
     */
    List<RouterDTO> getRouter(String userId);

    /**
     * 构建前端路由
     *
     * @param treeStruct 路由（菜单）列表
     * @return 路由列表
     */
    List<RouterDTO> buildRouter(List<RouteModel> treeStruct);

    /**
     * 获取用户信息用户名、权限集合等
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    UserInfoDTO getUserInfo(String userId);

}
