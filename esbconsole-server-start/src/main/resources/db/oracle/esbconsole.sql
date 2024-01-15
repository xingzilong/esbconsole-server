/*
 Source Server Type    : Oracle
 Source Server Version : 11g
*/

-- ----------------------------
-- 用户信息表
-- ----------------------------
CREATE TABLE system_user
(
    id           VARCHAR2(50)  NOT NULL,
    name         VARCHAR2(100) NOT NULL,
    user_name    VARCHAR2(50)  NOT NULL,
    password     VARCHAR2(256) NOT NULL,
    email        VARCHAR2(100) DEFAULT NULL,
    phone_number VARCHAR2(100) DEFAULT NULL,
    status       CHAR(1)       DEFAULT '1',
    delete_flag  CHAR(1)       DEFAULT '0',
    create_time  TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    update_time  TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT system_user_pk PRIMARY KEY (id),
    CONSTRAINT unique_user_name UNIQUE (user_name)
);

-- ----------------------------
-- 添加初始管理员用户
-- ----------------------------
INSERT INTO system_user (id, name, user_name, password, email, phone_number, status,
                         delete_flag,
                         create_time, update_time)
VALUES ('649343fd-caf1-40fd-8f2d-8709131dbb7a', '超级管理员', 'admin',
        '$2a$10$PL9ZlkdZuJ2r6Noh5BEnn.g2t4xiuCZVAI4eE2P/RnF94kwIaN5w2', NULL, NULL, '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));

-- ----------------------------
-- 角色信息表
-- ----------------------------
CREATE TABLE system_role
(
    id          VARCHAR2(50)  NOT NULL,
    name        VARCHAR2(50)  NOT NULL,
    role_name   VARCHAR2(100) NOT NULL,
    description VARCHAR2(100) DEFAULT NULL,
    status      CHAR(1)       DEFAULT '1',
    delete_flag CHAR(1)       DEFAULT '0',
    create_time TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT system_role_pk PRIMARY KEY (id),
    CONSTRAINT unique_role_name UNIQUE (name)
);


-- ----------------------------
-- 添加初始管理员角色
-- ----------------------------
INSERT INTO system_role (id, name, role_name, description, status, delete_flag,
                         create_time,
                         update_time)
VALUES ('47d87ccf-eaab-4506-8b37-f41002150ec8', '超级管理员', 'admin', '超级管理员，拥有系统所有权限', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));

-- ----------------------------
-- 权限信息表
-- ----------------------------
CREATE TABLE system_authority
(
    id              NUMBER        DEFAULT NULL,
    name            VARCHAR2(100) NOT NULL,
    parent_id       NUMBER        DEFAULT NULL,
    route_name      VARCHAR2(100) DEFAULT NULL,
    route_path      VARCHAR2(100) DEFAULT NULL,
    route_component VARCHAR2(100) DEFAULT NULL,
    order_num       NUMBER        DEFAULT NULL,
    route_level     NUMBER        DEFAULT NULL,
    icon            VARCHAR2(100) DEFAULT NULL,
    authority_code  VARCHAR2(50)  DEFAULT NULL,
    authority_key   VARCHAR2(100) DEFAULT NULL,
    description     VARCHAR2(100) DEFAULT NULL,
    type            CHAR(1)       DEFAULT NULL,
    status          CHAR(1)       DEFAULT '1',
    delete_flag     CHAR(1)       DEFAULT '0',
    create_time     TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    update_time     TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT system_authority_pk PRIMARY KEY (id)
);

-- ----------------------------
-- 添加初始权限信息
-- ----------------------------
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1, '用户管理', 0, 'user', '/user', 'layout', 5, 1, 'user-outlined', NULL, NULL, NULL, '0', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2, '服务管理', 0, 'service', '/service', 'layout', 2, 1, 'cloud-outlined', NULL, NULL, NULL, '0', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (3, '控制管理', 0, 'control', '/control', 'layout', 3, 1, 'control-outlined', NULL, NULL, NULL, '0', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (4, '监控管理', 0, 'monitor', '/monitor', 'layout', 4, 1, 'monitor-outlined', NULL, NULL, NULL, '0', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (5, '系统管理', 0, 'system', '/system', 'layout', 6, 1, 'setting-outlined', NULL, NULL, NULL, '0', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (6, '首页', 0, 'home', '/home', 'home/Home', 1, 1, 'home-outlined', NULL, NULL, NULL, '0', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (101, '用户', 1, 'users', '/user/users', 'user/Users', 1, 2, 'team-outlined', NULL, NULL, NULL, '0', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (102, '角色', 1, 'role', '/user/role', 'user/Role', 2, 2, 'user-switch-outlined', NULL, NULL, NULL, '0', '1',
        '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (103, '权限', 1, 'authority', '/user/authority', 'user/Authority', 3, 2, 'smile-outlined', NULL, NULL, NULL, '0',
        '1', '0', TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (201, '服务', 2, 'services', '/service/services', 'service/Services', 1, 2, 'api-outlined', NULL, NULL, NULL,
        '0',
        '1', '0', TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (202, '构件', 2, 'bundle', '/service/bundle', 'service/Bundle', 2, 2, 'appstore-outlined', NULL, NULL, NULL, '0',
        '1', '0', TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (203, '特性', 2, 'feature', '/service/feature', 'service/Feature', 3, 2, 'database-outlined', NULL, NULL, NULL,
        '0', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (204, '字典', 2, 'dictionary', '/service/dictionary', 'service/Dictionary', 4, 2, 'profile-outlined', NULL, NULL,
        NULL, '0', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (301, '访问控制', 3, 'accessControl', '/control/accessControl', 'control/AccessControl', 1, 2, 'swap-outlined',
        NULL,
        NULL, NULL, '0', '0', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (302, '告警控制', 3, 'alarmControl', '/control/alarmControl', 'control/AlarmControl', 2, 2, 'bell-outlined',
        NULL,
        NULL, NULL, '0', '0', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (401, '日志分析', 4, 'logAnalysis', '/monitor/logAnalysis', 'monitor/LogAnalysis', 5, 2, 'file-text-outlined',
        NULL,
        NULL, NULL, '0', '0', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (402, '告警详情', 4, 'alarm', '/monitor/alarm', 'monitor/Alarm', 3, 2, 'alert-outlined', NULL, NULL, NULL, '0',
        '0',
        '0', TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (404, '服务日志', 4, 'serviceLog', '/monitor/serviceLog', 'monitor/ServiceLog', 1, 2, 'file-search-outlined',
        NULL,
        NULL, NULL, '0', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (405, '任务日志', 4, 'jobLog', '/monitor/jobLog', 'monitor/JobLog', 2, 2, 'file-search-outlined', NULL, NULL,
        NULL,
        '0', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (501, '资源监控', 5, 'resourceMonitor', '/system/resourceMonitor', 'system/ResourceMonitor', 1, 2,
        'dashboard-outlined', NULL, NULL, NULL, '0', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (502, 'Karaf Console', 5, 'karafConsole', '/system/karafConsole', 'system/KarafConsole', 2, 2, 'code-outlined',
        NULL, NULL, NULL, '0', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (30101, 'IP控制', 3, 'ipControl', '/control/accessControl/ipControl', 'control/ac/IPControl', 1, 3,
        'vertical-left-outlined', NULL, NULL, NULL, '0', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (30102, '流量控制', 3, 'flowControl', '/control/accessControl/flowControl', 'control/ac/FlowControl', 2, 3,
        'line-chart-outlined', NULL, NULL, NULL, '0', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (30103, '频次控制', 3, 'frequencyControl', '/control/accessControl/frequencyControl',
        'control/ac/FrequencyControl', 3, 3, 'align-right-outlined', NULL, NULL, NULL, '0', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (40101, '容器日志', 401, 'containerLog', '/monitor/logAnalysis/containerLog', 'monitor/LogAnalysis', 1, 3,
        'file-outlined', NULL, NULL, NULL, '0', '0', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (40102, '平台日志', 401, 'platformLog', '/monitor/logAnalysis/platformLog', 'monitor/Alarm', 2, 3,
        'file-outlined',
        NULL, NULL, NULL, '0', '0', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1101001, '根据用户ID获取用户的详细信息', 101, NULL, NULL, NULL, NULL, NULL, NULL, '1-101-1',
        'user:user:getUser',
        '根据用户ID获取用户的详细信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1101002, '获取所有用户的详细信息', 101, NULL, NULL, NULL, NULL, NULL, NULL, '1-101-2', 'user:user:listAll',
        '获取所有用户的详细信息',
        '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1101003, '分页获取用户的详细信息', 101, NULL, NULL, NULL, NULL, NULL, NULL, '1-101-3', 'user:user:list',
        '分页获取用户的详细信息',
        '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1101004, '添加用户', 101, NULL, NULL, NULL, NULL, NULL, NULL, '1-101-4', 'user:user:add', '添加用户', '1', '1',
        '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1101005, '删除用户', 101, NULL, NULL, NULL, NULL, NULL, NULL, '1-101-5', 'user:user:remove', '删除用户', '1',
        '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1101006, '更改用户信息', 101, NULL, NULL, NULL, NULL, NULL, NULL, '1-101-6', 'user:user:modify', '更改用户信息',
        '1', '1',
        '0', TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1101007, '停用用户', 101, NULL, NULL, NULL, NULL, NULL, NULL, '1-101-7', 'user:user:disable', '停用用户', '1',
        '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1101008, '启用用户', 101, NULL, NULL, NULL, NULL, NULL, NULL, '1-101-8', 'user:user:enable', '启用用户', '1',
        '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1101009, '重置用户密码', 101, NULL, NULL, NULL, NULL, NULL, NULL, '1-101-9', 'user:user:resetPassword',
        '重置用户密码', '1',
        '1', '0', TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1101010, '获取所有用户名单以及用户拥有的用户', 101, NULL, NULL, NULL, NULL, NULL, NULL, '1-101-10',
        'user:user:getUserRoles',
        '获取所有用户名单以及用户拥有的用户', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1101011, '为用户分配角色', 101, NULL, NULL, NULL, NULL, NULL, NULL, '1-101-11', 'user:user:assignRole',
        '为用户分配角色', '1',
        '1', '0', TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1101012, '用户添加/修改的检查用户重复', 101, NULL, NULL, NULL, NULL, NULL, NULL, '1-101-12',
        'user:user:verifyUserName',
        '根据用户名称获取系统中是否已经包含该用户的详细信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1102001, '根据角色ID获取角色的详细信息', 102, NULL, NULL, NULL, NULL, NULL, NULL, '1-102-1',
        'user:role:getRole',
        '根据角色ID获取角色的详细信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1102002, '判断系统中是否存在特定角色的详细信息', 102, NULL, NULL, NULL, NULL, NULL, NULL, '1-102-2',
        'user:role:verifyRoleName',
        '判断系统中是否存在特定角色的详细信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1102003, '获取所有角色的详细信息', 102, NULL, NULL, NULL, NULL, NULL, NULL, '1-102-3', 'user:role:getAllRoles',
        '获取所有角色的详细信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1102004, '分页获取角色的详细信息', 102, NULL, NULL, NULL, NULL, NULL, NULL, '1-102-4', 'user:role:getRoles',
        '分页获取角色的详细信息',
        '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1102005, '添加角色', 102, NULL, NULL, NULL, NULL, NULL, NULL, '1-102-5', 'user:role:addRole', '添加角色', '1',
        '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1102006, '删除角色', 102, NULL, NULL, NULL, NULL, NULL, NULL, '1-102-6', 'user:role:removeRole', '删除角色',
        '1', '1',
        '0', TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1102007, '更改角色信息', 102, NULL, NULL, NULL, NULL, NULL, NULL, '1-102-7', 'user:role:modifyRole',
        '更改角色信息', '1',
        '1', '0', TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1102008, '停用角色', 102, NULL, NULL, NULL, NULL, NULL, NULL, '1-102-8', 'user:role:disableRole', '停用角色',
        '1', '1',
        '0', TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1102009, '启用角色', 102, NULL, NULL, NULL, NULL, NULL, NULL, '1-102-9', 'user:role:enableRole', '启用角色',
        '1', '1',
        '0', TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1102010, '分配权限功能的前置接口', 102, NULL, NULL, NULL, NULL, NULL, NULL, '1-102-10',
        'user:role:getAuthorityOtherInfo',
        '分配权限功能的前置接口', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1102011, '为角色分配权限', 102, NULL, NULL, NULL, NULL, NULL, NULL, '1-102-11',
        'user:role:assignAuthorityForRole',
        '为角色分配权限', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1102012, '获取该角色的使用者详细信息', 102, NULL, NULL, NULL, NULL, NULL, NULL, '1-102-12',
        'user:role:getInUsing',
        '获取该角色的使用者详细信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1102013, '取消用户的某个角色授权', 102, NULL, NULL, NULL, NULL, NULL, NULL, '1-102-13',
        'user:role:cancelAuthority',
        '取消用户的某个角色授权', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1103001, '根据权限ID获取权限的详细信息', 103, NULL, NULL, NULL, NULL, NULL, NULL, '1-103-1',
        'user:authority:getAuthority',
        '根据权限ID获取权限的详细信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1103002, '获取所有权限的详细信息', 103, NULL, NULL, NULL, NULL, NULL, NULL, '1-103-2',
        'user:authority:getAllAuthorities',
        '获取所有权限的详细信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (1103003, '获取所有权限（和路由）的详细信息，树形结构', 103, NULL, NULL, NULL, NULL, NULL, NULL, '1-103-3',
        'user:authority:getAllAuthoritiesTree', '获取所有权限（和路由）的详细信息，树形结构', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2201001, '获取所有的服务的详细信息', 201, NULL, NULL, NULL, NULL, NULL, NULL, '2-201-1',
        'service:service:listAll',
        '获取所有的服务的详细信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2201002, '分页获取服务的详细信息', 201, NULL, NULL, NULL, NULL, NULL, NULL, '2-201-2', 'service:service:list',
        '分页获取服务的详细信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2201003, '获取某个服务下的Bundle的详细信息', 201, NULL, NULL, NULL, NULL, NULL, NULL, '2-201-3',
        'service:service:listBundles', '获取某个服务下的Bundle的详细信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2201004, '部署服务', 201, NULL, NULL, NULL, NULL, NULL, NULL, '2-201-4', 'service:service:install', '部署服务',
        '1',
        '1', '0', TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2201005, '卸载服务', 201, NULL, NULL, NULL, NULL, NULL, NULL, '2-201-5', 'service:service:uninstall',
        '卸载服务', '1',
        '1', '0', TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2201006, '启动服务', 201, NULL, NULL, NULL, NULL, NULL, NULL, '2-201-6', 'service:service:start', '启动服务',
        '1', '1',
        '0', TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2201007, '停止服务', 201, NULL, NULL, NULL, NULL, NULL, NULL, '2-201-7', 'service:service:stop', '停止服务',
        '1', '1',
        '0', TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2201008, '修改服务信息', 201, NULL, NULL, NULL, NULL, NULL, NULL, '2-201-8', 'service:service:modify',
        '修改服务信息', '1',
        '1', '0', TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2201009, '获取api调用top5数据', 201, NULL, NULL, NULL, NULL, NULL, NULL, '2-201-9', 'service:service:apiTop5',
        '获取api调用top5数据', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2201010, '获取api失败调用top5数据', 201, NULL, NULL, NULL, NULL, NULL, NULL, '2-201-10',
        'service:service:getFTTop5',
        '获取api失败调用top5数据', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2201011, '获取api调用分析表格', 201, NULL, NULL, NULL, NULL, NULL, NULL, '2-201-11', 'service:service:getApiCA',
        '获取api调用分析表格', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2201012, '获取api接口响应次数分析', 201, NULL, NULL, NULL, NULL, NULL, NULL, '2-201-12',
        'service:service:getApiTA',
        '获取api接口响应次数分析', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2201013, '获取api接口响应耗时分析', 201, NULL, NULL, NULL, NULL, NULL, NULL, '2-201-13',
        'service:service:getApiRTA',
        '获取api接口响应耗时分析', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2201014, '获取api接口报文大小分析', 201, NULL, NULL, NULL, NULL, NULL, NULL, '2-201-14',
        'service:service:getApiMSA',
        '获取api接口报文大小分析', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2201015, '定时任务分析-任务执行成功失败', 201, NULL, NULL, NULL, NULL, NULL, NULL, '2-201-15',
        'service:service:getTaskEC',
        '定时任务分析-任务执行成功失败', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2201016, '定时任务分析-执行时间分析', 201, NULL, NULL, NULL, NULL, NULL, NULL, '2-201-16',
        'service:service:getTaskTCC',
        '定时任务分析-执行时间分析', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2201017, '定时任务分析-定时任务分析表格分页查询', 201, NULL, NULL, NULL, NULL, NULL, NULL, '2-201-17',
        'service:service:getTaskTT', '定时任务分析-定时任务分析表格分页查询', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2201018, '根据ID获取某个服务的详细信息', 201, NULL, NULL, NULL, NULL, NULL, NULL, '2-201-18',
        'service:service:getService',
        '根据ID获取某个服务的详细信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2202001, '获取所有的Bundle的详细信息', 202, NULL, NULL, NULL, NULL, NULL, NULL, '2-202-1',
        'service:bundle:listAll',
        '获取所有的Bundle的详细信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2202002, '启动Bundle', 202, NULL, NULL, NULL, NULL, NULL, NULL, '2-202-2', 'service:bundle:start', '启动Bundle',
        '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2202003, '停止Bundle', 202, NULL, NULL, NULL, NULL, NULL, NULL, '2-202-3', 'service:bundle:stop', '停止Bundle',
        '1',
        '1', '0', TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2202004, '分页获取bundle信息', 202, NULL, NULL, NULL, NULL, NULL, NULL, '2-202-4', 'service:bundle:list',
        '分页获取bundle信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2203001, '获取所有的Feature的详细信息', 203, NULL, NULL, NULL, NULL, NULL, NULL, '2-203-1',
        'service:feature:listAll',
        '获取所有的Feature的详细信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2203002, '根据某个Feature下的mvnBundleName获取某个Feature下的Bundle的详细信息', 203, NULL, NULL, NULL, NULL,
        NULL, NULL,
        '2-203-2', 'service:feature:listBundles', '根据某个Feature下的mvnBundleName获取某个Feature下的Bundle的详细信息',
        '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2203003, '安装Feature', 203, NULL, NULL, NULL, NULL, NULL, NULL, '2-203-3', 'service:feature:install',
        '安装Feature', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2203004, '卸载Feature', 203, NULL, NULL, NULL, NULL, NULL, NULL, '2-203-4', 'service:feature:uninstall',
        '卸载Feature', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2203005, '分页获取feature信息', 203, NULL, NULL, NULL, NULL, NULL, NULL, '2-203-5', 'service:feature:list',
        '分页获取feature信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2204001, '根据消费方系统ID获取消费方系统的详细信息', 204, NULL, NULL, NULL, NULL, NULL, NULL, '2-204-1',
        'service:dictionary:getConsumer', '根据消费方系统ID获取消费方系统的详细信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2204002, '根据消费方系统IP地址获取是否包含该消费方系统的详细信息', 204, NULL, NULL, NULL, NULL, NULL, NULL,
        '2-204-2',
        'service:dictionary:verifyConsumerIp', '根据消费方系统IP地址获取是否包含该消费方系统的详细信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2204003, '分页获取消费方系统的详细信息', 204, NULL, NULL, NULL, NULL, NULL, NULL, '2-204-3',
        'service:dictionary:getConsumers', '分页获取消费方系统的详细信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2204004, '添加消费方系统', 204, NULL, NULL, NULL, NULL, NULL, NULL, '2-204-4', 'service:dictionary:addConsumer',
        '添加消费方系统', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2204005, '删除消费方系统', 204, NULL, NULL, NULL, NULL, NULL, NULL, '2-204-5',
        'service:dictionary:removeConsumer',
        '删除消费方系统', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2204006, '更改消费方系统信息', 204, NULL, NULL, NULL, NULL, NULL, NULL, '2-204-6',
        'service:dictionary:modifyConsumer',
        '更改消费方系统信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2204007, '停用消费方系统', 204, NULL, NULL, NULL, NULL, NULL, NULL, '2-204-7',
        'service:dictionary:disableConsumer',
        '停用消费方系统', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2204008, '启用消费方系统', 204, NULL, NULL, NULL, NULL, NULL, NULL, '2-204-8',
        'service:dictionary:enableConsumer',
        '启用消费方系统', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2204009, '根据业务系统ID获取业务系统的详细信息', 204, NULL, NULL, NULL, NULL, NULL, NULL, '2-204-9',
        'service:dictionary:getBusinessById', '根据业务系统ID获取业务系统的详细信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2204010, '业务系统系统添加/修改检查重复IP', 204, NULL, NULL, NULL, NULL, NULL, NULL, '2-204-10',
        'service:dictionary:verifyBusinessSN', '根据业务系统名称获取是否包含该业务系统名称的信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2204011, '分页获取业务系统的详细信息', 204, NULL, NULL, NULL, NULL, NULL, NULL, '2-204-11',
        'service:dictionary:getBusinesss', '分页获取业务系统的详细信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2204012, '获取业务系统的所有系统名称', 204, NULL, NULL, NULL, NULL, NULL, NULL, '2-204-12',
        'service:dictionary:getAllBusinessSN', '获取业务系统的所有系统名称', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2204013, '添加业务系统', 204, NULL, NULL, NULL, NULL, NULL, NULL, '2-204-13', 'service:dictionary:addBusiness',
        '添加业务系统', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2204014, '删除业务系统', 204, NULL, NULL, NULL, NULL, NULL, NULL, '2-204-14',
        'service:dictionary:removeBusiness',
        '添加业务系统', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2205015, '更改业务系统信息', 204, NULL, NULL, NULL, NULL, NULL, NULL, '2-204-15',
        'service:dictionary:modifyBusiness',
        '更改业务系统信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2205016, '停用业务系统', 204, NULL, NULL, NULL, NULL, NULL, NULL, '2-204-16',
        'service:dictionary:disableBusiness',
        '停用业务系统', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (2205017, '停用业务系统', 204, NULL, NULL, NULL, NULL, NULL, NULL, '2-204-17',
        'service:dictionary:enableBusiness',
        '启用业务系统', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (4404001, '分页获取服务的日志信息', 404, NULL, NULL, NULL, NULL, NULL, NULL, '4-404-1',
        'monitor:serviceLog:getServiceLogs',
        '分页获取服务的日志信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (4404002, '获取请求报文', 404, NULL, NULL, NULL, NULL, NULL, NULL, '4-404-2',
        'monitor:serviceLog:getRequestMessage',
        '获取请求报文', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (4404003, '获取响应报文', 404, NULL, NULL, NULL, NULL, NULL, NULL, '4-404-3',
        'monitor:serviceLog:getResponseMessage',
        '获取响应报文', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (4405001, '分页获取服务的日志信息', 405, NULL, NULL, NULL, NULL, NULL, NULL, '4-405-1',
        'monitor:jobLog:getJobLogs',
        '分页获取服务的日志信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (4405002, '获取该次任务运行的分析数据', 405, NULL, NULL, NULL, NULL, NULL, NULL, '4-405-2',
        'monitor:jobLog:getJobLogAnalysis', '获取该次任务运行的分析数据', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (5501001, '获取服务器资源信息', 501, NULL, NULL, NULL, NULL, NULL, NULL, '5-501-1',
        'system:server:getServerInfo',
        '获取服务器资源信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (5501002, '获取ESBServer的JVM内存信息', 501, NULL, NULL, NULL, NULL, NULL, NULL, '5-501-2',
        'system:server:getESBServerJVMMemoryInfo', '获取ESBServer的JVM内存信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (5501003, '获取ESBServer的JVM信息', 501, NULL, NULL, NULL, NULL, NULL, NULL, '5-501-3',
        'system:server:getESBServerJVMInfo', '获取ESBServer的JVM信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (5502001, 'Karaf Console ws连接权限', 502, NULL, NULL, NULL, NULL, NULL, NULL, '5-502-1',
        'system:karafconsole:websocket', 'Karaf Console ws连接权限', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (6000001, '首页顶部数据栏数据获取', 6, NULL, NULL, NULL, NULL, NULL, NULL, '6-1', 'home:analysis:getContentTD',
        '首页顶部数据栏数据获取', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (6000002, '首页应用程序分析图表数据获取', 6, NULL, NULL, NULL, NULL, NULL, NULL, '6-2', 'home:analysis:getAAL',
        '首页应用程序分析图表数据获取', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (6000003, '首页成功&失败图表数据', 6, NULL, NULL, NULL, NULL, NULL, NULL, '6-3', 'home:analysis:getApiCSL',
        '首页成功&失败图表数据',
        '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (6000004, '首页近七天任务执行情况图表数据获取', 6, NULL, NULL, NULL, NULL, NULL, NULL, '6-4',
        'home:analysis:getTaskPRD',
        '首页近七天任务执行情况图表数据获取', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (6000005, '首页近七天API调数据量统计', 6, NULL, NULL, NULL, NULL, NULL, NULL, '6-5', 'home:analysis:getDataCT4D',
        '首页近七天API调数据量统计', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (6000006, '首页近七天API次数统计', 6, NULL, NULL, NULL, NULL, NULL, NULL, '6-6', 'home:analysis:getCT4D',
        '首页近七天API次数统计',
        '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (330101001, '分页获取IP控制规则的的详细信息', 30101, NULL, NULL, NULL, NULL, NULL, NULL, '3-301-1',
        'control:ac:getIPControls',
        '分页获取IP控制规则的的详细信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (330101002, '根据id获取一条IP规则信息', 30101, NULL, NULL, NULL, NULL, NULL, NULL, '3-301-2',
        'control:ac:getIPControlById', '根据id获取一条IP规则信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (330101003, '获取未绑定IP规则的服务', 30101, NULL, NULL, NULL, NULL, NULL, NULL, '3-301-3',
        'control:ac:getServiceNoBIPC',
        '获取未绑定IP规则的服务', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (330101004, '添加IP规则信息', 30101, NULL, NULL, NULL, NULL, NULL, NULL, '3-301-4', 'control:ac:addIPControl',
        '添加IP规则信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (330101005, '更改IP规则信息', 30101, NULL, NULL, NULL, NULL, NULL, NULL, '3-301-5', 'control:ac:modifyIPControl',
        '更改IP规则信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (330101006, '停用IP控制规则', 30101, NULL, NULL, NULL, NULL, NULL, NULL, '3-301-6',
        'control:ac:disableIPControl',
        '停用IP控制规则', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (330101007, '启用IP控制规则', 30101, NULL, NULL, NULL, NULL, NULL, NULL, '3-301-7', 'control:ac:enableIPControl',
        '启用IP控制规则', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (330101008, '删除IP控制规则', 30101, NULL, NULL, NULL, NULL, NULL, NULL, '3-301-8', 'control:ac:removeIPControl',
        '删除IP控制规则', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (330102001, '分页获取流量控制规则的详细信息', 30102, NULL, NULL, NULL, NULL, NULL, NULL, '3-301-17',
        'control:ac:getFlowControls', '分页获取流量控制规则的详细信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (330102002, '根据id获取一条流量规则信息', 30102, NULL, NULL, NULL, NULL, NULL, NULL, '3-301-18',
        'control:ac:getFlowControlById', '根据id获取一条流量规则信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (330102003, '获取未绑定流量规则的服务', 30102, NULL, NULL, NULL, NULL, NULL, NULL, '3-301-19',
        'control:ac:getServiceNBFlowC', '获取未绑定流量规则的服务', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (330102004, '添加流量规则信息', 30102, NULL, NULL, NULL, NULL, NULL, NULL, '3-301-20',
        'control:ac:addFlowControl',
        '添加流量规则信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (330102005, '更改流量规则信息', 30102, NULL, NULL, NULL, NULL, NULL, NULL, '3-301-21',
        'control:ac:modifyFlowControl',
        '更改流量规则信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (330102006, '停用流量控制规则', 30102, NULL, NULL, NULL, NULL, NULL, NULL, '3-301-22',
        'control:ac:disableFlowControl',
        '停用流量控制规则', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (330102007, '启用流量控制规则', 30102, NULL, NULL, NULL, NULL, NULL, NULL, '3-301-23',
        'control:ac:enableFlowControl',
        '启用流量控制规则', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (330102008, '删除流量控制规则', 30102, NULL, NULL, NULL, NULL, NULL, NULL, '3-301-24',
        'control:ac:removeFlowControl',
        '删除流量控制规则', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (330103001, '分页获取频次控制规则的的详细信息', 30103, NULL, NULL, NULL, NULL, NULL, NULL, '3-301-9',
        'control:ac:getFrequencyControls', '分页获取频次控制规则的的详细信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (330103002, '根据id获取一条频次规则信息', 30103, NULL, NULL, NULL, NULL, NULL, NULL, '3-301-10',
        'control:ac:getFrequencyControlById', '根据id获取一条频次规则信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (330103003, '获取未绑定频次规则的服务', 30103, NULL, NULL, NULL, NULL, NULL, NULL, '3-301-11',
        'control:ac:getServiceNoBFC',
        '获取未绑定频次规则的服务', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (330103004, '添加频次规则信息', 30103, NULL, NULL, NULL, NULL, NULL, NULL, '3-301-12',
        'control:ac:addFrequencyControl',
        '添加频次规则信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (330103005, '更改频次规则信息', 30103, NULL, NULL, NULL, NULL, NULL, NULL, '3-301-13',
        'control:ac:modifyFrequencyControl', '更改频次规则信息', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (330103006, '停用频次控制规则', 30103, NULL, NULL, NULL, NULL, NULL, NULL, '3-301-14',
        'control:ac:disableFrequencyControl', '停用频次控制规则', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (330103007, '启用频次控制规则', 30103, NULL, NULL, NULL, NULL, NULL, NULL, '3-301-15',
        'control:ac:enableFrequencyControl', '启用频次控制规则', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_authority (id, name, parent_id, route_name, route_path,
                              route_component, order_num,
                              route_level, icon, authority_code, authority_key, description, type,
                              status, delete_flag, create_time, update_time)
VALUES (330103008, '删除频次控制规则', 30103, NULL, NULL, NULL, NULL, NULL, NULL, '3-301-16',
        'control:ac:removeFrequencyControl', '删除频次控制规则', '1', '1', '0',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));

-- ----------------------------
-- 用户-角色关系表
-- ----------------------------
CREATE TABLE system_user_role
(
    id          VARCHAR2(50) NOT NULL,
    user_id     VARCHAR2(50) NOT NULL,
    role_id     VARCHAR2(50) NOT NULL,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT system_user_role_pk PRIMARY KEY (id)
);


-- ----------------------------
-- 添加初始管理员用户-角色映射关系
-- ----------------------------
INSERT INTO system_user_role (id, user_id, role_id, create_time, update_time)
VALUES ('49251c09-e386-4e40-9690-a224f5b9c840', '649343fd-caf1-40fd-8f2d-8709131dbb7a',
        '47d87ccf-eaab-4506-8b37-f41002150ec8',
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));

-- ----------------------------
-- 角色_权限关联表
-- ----------------------------
CREATE TABLE system_role_authority
(
    id           VARCHAR2(50) NOT NULL,
    role_id      VARCHAR2(50) NOT NULL,
    authority_id NUMBER       NOT NULL,
    create_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT system_role_authority_pk PRIMARY KEY (id)
);


-- ----------------------------
-- 添加初始管理员角色-权限映射关系
-- ----------------------------
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('009a20ff-07b9-4fd8-ac96-1ba0f79534fd', '47d87ccf-eaab-4506-8b37-f41002150ec8', 330103006,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('0213c125-1845-48cc-808a-835684411ab3', '47d87ccf-eaab-4506-8b37-f41002150ec8', 330101004,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('040bf862-854a-479a-b216-e7c09fda6b5e', '47d87ccf-eaab-4506-8b37-f41002150ec8', 6000004,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('0514d1e8-e880-4b86-a26e-dd8c987d0021', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2202003,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('0ddeba12-bc21-4d7a-b41c-23ab595b6652', '47d87ccf-eaab-4506-8b37-f41002150ec8', 101,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('0f16d3ed-8d65-4f90-8c2b-fd973da37568', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2201018,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('0fc40724-e65f-44c8-ad77-b379d9a629da', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1101012,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('10512e6e-373e-43a5-b479-bfe264e0ac0c', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2204011,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('13fa1d4a-a9c7-4440-849c-8371997797cb', '47d87ccf-eaab-4506-8b37-f41002150ec8', 330102002,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('160a54ad-d282-4f21-8783-c6424994c7a4', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1101004,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('16dced40-9a4b-4a40-9df0-bb4584773873', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1102001,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('1b3bcf02-340e-4d9f-a69e-78c4b73c73ec', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2204006,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('2296ab86-d385-40ee-ba7f-7b23525cad96', '47d87ccf-eaab-4506-8b37-f41002150ec8', 330101007,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('27b06c18-be6a-4b86-b740-7e6684635cc2', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2201015,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('27b6c6c6-f5de-41d5-85a9-f632be9f256d', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2205017,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('27bf3e02-ee5e-469f-8e16-97a5fcc31171', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1102007,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('27fed47a-5a8a-431f-9dbc-55bc4720aa37', '47d87ccf-eaab-4506-8b37-f41002150ec8', 203,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('28e11994-3513-4736-96e1-0a7333b9c9f3', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2202004,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('2c75d994-2d41-4a98-9379-573c91b76752', '47d87ccf-eaab-4506-8b37-f41002150ec8', 5501003,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('2e4de2bd-8325-47de-9535-5aaa214191ab', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('2fa08a84-1450-41f2-bc69-6ab0857f73ca', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('3320c4d1-7fc7-4e71-9c5c-499877e0285f', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2204009,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('37ab6cc0-8a13-4b74-99cc-20541d59c578', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2201014,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('39356be2-2e33-49c3-88d6-e5b3b048f391', '47d87ccf-eaab-4506-8b37-f41002150ec8', 330103008,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('3ae714ae-9bc8-43cc-9d95-e5f683554872', '47d87ccf-eaab-4506-8b37-f41002150ec8', 330102007,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('3ce338b3-30cb-406e-90d9-fd3b7f70fceb', '47d87ccf-eaab-4506-8b37-f41002150ec8', 5,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('3db209d4-c5a5-4e7b-a9bf-2351d36c498f', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2204002,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('3ff49d9c-3ae7-44ad-83ab-53fd30abde25', '47d87ccf-eaab-4506-8b37-f41002150ec8', 3,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('4097d467-9471-4d50-9018-fe4801bba56d', '47d87ccf-eaab-4506-8b37-f41002150ec8', 5501001,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('40cb86df-a9b4-4adb-8d3c-d9bbaf8c5f65', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1102008,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('4116d4e1-d78f-4bcf-bac9-58871d1e7627', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2201003,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('412b115d-bc80-4124-aca1-52741a3dc101', '47d87ccf-eaab-4506-8b37-f41002150ec8', 30103,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('41af1da2-18af-44b7-a14b-18c5d54db67c', '47d87ccf-eaab-4506-8b37-f41002150ec8', 4404002,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('421c6dc2-1ee2-499c-8ac1-85de0fe0e166', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1101009,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('4478aca2-0de7-4d29-8a79-a83264392f45', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2201001,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('46d7a1a6-2f36-462c-a95d-b5af8241cb66', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2204008,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('47f0b008-09bc-4268-b6bd-9230318c0bb6', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1101006,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('49533bcd-073f-43c7-a554-446f65dec130', '47d87ccf-eaab-4506-8b37-f41002150ec8', 4404001,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('4dd1a4a7-580b-4ed9-908c-8dc9bfde4422', '47d87ccf-eaab-4506-8b37-f41002150ec8', 330101003,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('4f38f416-e39c-4f21-9841-8516b87cd3d8', '47d87ccf-eaab-4506-8b37-f41002150ec8', 330103001,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('4f48f6e3-e63a-4f95-b92a-a42891fff3c2', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1102009,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('52115c95-19ef-4c92-8367-1eeff6d80cdb', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1102003,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('561ecc50-fb7c-450d-a493-57fdf5e72980', '47d87ccf-eaab-4506-8b37-f41002150ec8', 201,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('57e4f9a5-d3ed-4ae4-b28e-fc6dd110deed', '47d87ccf-eaab-4506-8b37-f41002150ec8', 103,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('59942be3-1329-4321-b60a-e6dc937c0503', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1102002,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('59c9bec3-a4ed-4073-9e49-35751eab6ee9', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2203002,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('5c8a5f7e-4816-4352-9223-eaa01f4607f8', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1101005,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('5ccea4a6-796b-4dd5-a17e-771ce6a0ee2a', '47d87ccf-eaab-4506-8b37-f41002150ec8', 330102003,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('5d2f2dd4-dd39-464b-8df0-f3e6cbc4f5df', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2201017,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('5d80ae1e-ce9f-4c68-9079-d2cb0b0af9ca', '47d87ccf-eaab-4506-8b37-f41002150ec8', 330101005,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('5dcb5458-3390-4c61-82cf-d4cdca69c408', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2202002,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('61347176-ceb2-4e96-8f8f-f3c2ad3a977c', '47d87ccf-eaab-4506-8b37-f41002150ec8', 102,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('64711088-7fa1-471b-9017-c7d9fda805f2', '47d87ccf-eaab-4506-8b37-f41002150ec8', 330102006,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('6c909e5d-0398-423d-b61d-7219d219e594', '47d87ccf-eaab-4506-8b37-f41002150ec8', 330101008,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('6da91b17-dc8c-46eb-994b-548f85f3f19c', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2203004,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('7147e2bc-a4e0-4cbb-bd68-f904b07fd162', '47d87ccf-eaab-4506-8b37-f41002150ec8', 330102005,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('730a23bd-c3ea-4804-9f12-d3ccbcf2d392', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2204012,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('758e199f-717e-463f-9948-05b1df3a525a', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2201007,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('75e44e4a-3d83-4532-8552-1504d1aa3918', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1101010,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('78cef906-55f3-4ace-8569-f4a4b19295ee', '47d87ccf-eaab-4506-8b37-f41002150ec8', 405,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('7a061953-5a43-4820-87c2-da19b2071d61', '47d87ccf-eaab-4506-8b37-f41002150ec8', 5502001,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('7bbb1be5-78d5-4871-b0e7-089aed3908a1', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1102012,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('7d02e12a-00f6-4aad-91dc-d6e57b6bbd2b', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2201009,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('7e865928-3e8e-4fcc-a431-33db44b46103', '47d87ccf-eaab-4506-8b37-f41002150ec8', 330102001,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('7f21038a-26a0-47f6-9fca-449a2810798e', '47d87ccf-eaab-4506-8b37-f41002150ec8', 330101002,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('828d1c4e-21c6-4555-9884-84698a0bbc7d', '47d87ccf-eaab-4506-8b37-f41002150ec8', 404,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('839fcbac-2939-4dd6-a838-7da4cc35cc76', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2201016,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('87fc2672-e7bf-4d04-b058-ac446bc1f060', '47d87ccf-eaab-4506-8b37-f41002150ec8', 204,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('889bd88f-941e-4769-9640-76918e84510e', '47d87ccf-eaab-4506-8b37-f41002150ec8', 330103005,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('893b4bfe-af0a-47a6-836f-cae3f4850936', '47d87ccf-eaab-4506-8b37-f41002150ec8', 6,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('89b451d9-1072-4007-9064-bb84090d1d30', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2205015,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('89c7813a-9a57-4d3b-b9eb-6dfe68c836a5', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2204013,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('8e3f9eb7-72fc-4dd4-923c-17799c2afa0a', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2201005,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('9200a86a-a43a-43f2-a9ff-d5cc8c15b263', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2201011,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('92642a98-e13a-4182-bf1b-ca4aa20c0fbe', '47d87ccf-eaab-4506-8b37-f41002150ec8', 6000002,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('93f38ab2-d89d-40d4-8152-a794e4753e48', '47d87ccf-eaab-4506-8b37-f41002150ec8', 5501002,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('950717bc-3611-4572-9863-bd0b969c3253', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2204005,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('955f7749-8412-4e34-8ca2-dce56426e647', '47d87ccf-eaab-4506-8b37-f41002150ec8', 301,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('95a9d4ba-aafc-4e7d-bfcc-631da6332627', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2202001,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('981868ce-568b-4e33-9acb-4d8b2b2a0a21', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1102005,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('992cd42b-2ed2-4a58-8a82-136c74ec772d', '47d87ccf-eaab-4506-8b37-f41002150ec8', 6000001,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('9d31b5a8-d374-44cb-ac3b-dd5069a8ff59', '47d87ccf-eaab-4506-8b37-f41002150ec8', 330102008,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('9d76ce78-97a5-45a3-882d-e636400c8f49', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2204010,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('9d9c64d8-8134-4887-abcf-7801293880b2', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2204007,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('9f699e66-e0c3-4240-91c7-1ab36f1df734', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1102006,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('9f85da0a-a1f4-4129-bf93-f558903c1da1', '47d87ccf-eaab-4506-8b37-f41002150ec8', 330103004,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('a0853ed1-067d-4107-b690-3c26cc61558d', '47d87ccf-eaab-4506-8b37-f41002150ec8', 330101001,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('a3d78448-0618-49ad-af45-b70c94eea1fc', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2204014,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('a7a0649f-634e-4852-bafa-351e1c261f0b', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1101002,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('a972cf0a-abd6-40c5-b8a0-8c370a3671ac', '47d87ccf-eaab-4506-8b37-f41002150ec8', 330103003,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('af3ee2d4-87e7-493c-b6ae-0234cedbcb46', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1103001,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('b1ac4140-29a5-4874-9f66-32ec4dffbe03', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2204001,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('b2e006cb-8c8e-4aac-8683-e2d7bb760ddd', '47d87ccf-eaab-4506-8b37-f41002150ec8', 30101,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('b36506fd-0230-4a2c-a5dd-c941cc2c0874', '47d87ccf-eaab-4506-8b37-f41002150ec8', 4404003,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('b4069f32-36b8-454d-97fe-18b74e8452ce', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1102011,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('b524d37c-49a2-4ccf-a447-350d3ce127e0', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1101011,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('b6a8c3df-7d57-4842-b25a-77fea367e04f', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2203003,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('b9bc012f-e4c9-4af6-84ef-af7ad8e1c65c', '47d87ccf-eaab-4506-8b37-f41002150ec8', 330102004,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('ba31da3f-b4d3-43e7-8f3e-16b64811841e', '47d87ccf-eaab-4506-8b37-f41002150ec8', 4405002,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('bdc84543-d7a4-4b24-9dbe-b9ccd5271143', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2201010,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('bf8ae010-a8ea-4ec5-82fb-08c23e828d2e', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2201004,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('c1487ebb-b7f4-4e27-9189-a782cba875d4', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2204003,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('c18458ed-d789-4551-bde2-f9b7cd7ff44f', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2203001,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('cc38c948-a6aa-4fbf-8c04-306d26db23a9', '47d87ccf-eaab-4506-8b37-f41002150ec8', 6000003,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('cc79cfec-ab5a-48a2-8304-05d0029fe8e2', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2203005,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('ccb57201-39f6-420c-9e45-6bc7735f2bfc', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1102004,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('cf615311-cade-4842-8264-8dcc96d7c348', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1101003,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('d2d2a3ec-1624-48d2-901b-7b2a3d67874c', '47d87ccf-eaab-4506-8b37-f41002150ec8', 4,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('d2e26af7-4b5f-496f-9f67-b0ec7e839148', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2204004,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('d78680f9-45a3-4de3-b7f9-dc76a77bcd93', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2201012,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('d7bdde8b-00c8-4e65-b7a8-c7a8fd5852af', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1102010,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('d8915540-926f-4eba-9f3a-f1f5335cd4fa', '47d87ccf-eaab-4506-8b37-f41002150ec8', 330103007,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('dbf7a193-3a9c-4394-91bd-653599e9025d', '47d87ccf-eaab-4506-8b37-f41002150ec8', 502,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('dd251901-c5a9-488c-97b8-59e367b1ddd3', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2201002,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('de9cf534-eceb-491d-b12e-8bd968fb97f7', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2201006,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('e2de5f4a-94a9-4ac5-be0a-ac4a9f9d3a74', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1101008,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('e5ba9070-781f-4543-8cf7-79755166f127', '47d87ccf-eaab-4506-8b37-f41002150ec8', 30102,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('e696699a-2090-42fe-892b-1b4b257159ea', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1103003,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('e6b14c61-5a8c-4b45-ab0a-4ee5c18b0bb1', '47d87ccf-eaab-4506-8b37-f41002150ec8', 501,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('e729726b-8127-4e52-a745-f158e5fde101', '47d87ccf-eaab-4506-8b37-f41002150ec8', 6000005,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('e785bf07-dff8-44f2-90c2-e7d2bf79bd52', '47d87ccf-eaab-4506-8b37-f41002150ec8', 4405001,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('e91a0e2f-a86f-4f70-aad8-26bc12ec19c8', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2201013,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('eb1a2783-ce6b-4395-a683-6dc7b742fa4f', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1101007,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('eccf09e1-55ba-4c4b-b9ca-292d500f7dd6', '47d87ccf-eaab-4506-8b37-f41002150ec8', 330103002,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('ef69de0b-8a33-4757-8880-01df926c5ebc', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1102013,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('ef867817-64cb-462e-a6f4-024c2fcd5e35', '47d87ccf-eaab-4506-8b37-f41002150ec8', 6000006,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('f2fa08af-faca-4b89-ac2b-e5864c7653c3', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2205016,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('f77d834e-24e0-49cc-91b6-b8c197131b38', '47d87ccf-eaab-4506-8b37-f41002150ec8', 202,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('f835f140-f9b2-4f0e-9485-5bef328fa74c', '47d87ccf-eaab-4506-8b37-f41002150ec8', 2201008,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('f9754b51-7074-46b2-b92a-f36fd6553b08', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1101001,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('fcd5f5e7-c1f6-4884-bbe5-dd2b9ae9a87a', '47d87ccf-eaab-4506-8b37-f41002150ec8', 1103002,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO system_role_authority (id, role_id, authority_id, create_time, update_time)
VALUES ('ffbaa352-526a-4c5f-9858-27e4d10afb27', '47d87ccf-eaab-4506-8b37-f41002150ec8', 330101006,
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
-- ----------------------------
-- 服务信息表
-- ----------------------------
CREATE TABLE service
(
    id                 VARCHAR2(50) NOT NULL,
    name               VARCHAR2(100) DEFAULT NULL,
    business_system_id VARCHAR2(100) DEFAULT NULL,
    service_type       VARCHAR2(100) DEFAULT NULL,
    job                CLOB,
    file_name          VARCHAR2(100) DEFAULT NULL,
    file_type          VARCHAR2(100) DEFAULT NULL,
    bundle_id          NUMBER        DEFAULT NULL,
    feature_name       VARCHAR2(100) DEFAULT NULL,
    bundle_name        CLOB,
    service_key        VARCHAR2(100) DEFAULT NULL,
    enabled_sam        CHAR(1)       DEFAULT '0',
    status             CHAR(1)       DEFAULT '1',
    description        VARCHAR2(100) DEFAULT NULL,
    responsible_person VARCHAR2(100) DEFAULT NULL,
    manifest_info      CLOB,
    create_time        TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    update_time        TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT service_pk PRIMARY KEY (id)
);

-- ----------------------------
-- 业务系统字典表
-- ----------------------------
CREATE TABLE business_system
(
    id                 VARCHAR2(50) NOT NULL,
    system_name        VARCHAR2(20)  DEFAULT NULL,
    status             CHAR(1)       DEFAULT NULL,
    responsible_person VARCHAR2(100) DEFAULT NULL,
    remark             VARCHAR2(255) DEFAULT NULL,
    create_time        TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    update_time        TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT business_system_pk PRIMARY KEY (id)
);


-- ----------------------------
-- 消费方系统字典表
-- ----------------------------
CREATE TABLE consumer_system
(
    id                 VARCHAR2(50) NOT NULL,
    ip                 VARCHAR2(100) DEFAULT NULL,
    system_name        VARCHAR2(50)  DEFAULT NULL,
    status             CHAR(1)       DEFAULT NULL,
    responsible_person VARCHAR2(100) DEFAULT NULL,
    remark             VARCHAR2(100) DEFAULT NULL,
    create_time        TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    update_time        TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT consumer_system_pk PRIMARY KEY (id)
);

-- ----------------------------
-- esb-server相关数据库表
-- ----------------------------
-- 访问控制规则定义表
-- CREATE TABLE access_control_ip
-- (
--     id          VARCHAR2(100) NOT NULL,
--     service_key VARCHAR2(255) DEFAULT NULL,
--     type        VARCHAR2(100) DEFAULT NULL,
--     black_list  CLOB,
--     white_list  CLOB,
--     status      CHAR(1)   DEFAULT '1',
--     create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     PRIMARY KEY (id)
-- );
--
-- CREATE TABLE access_control_flow
-- (
--     id                 VARCHAR2(100) NOT NULL,
--     service_key        VARCHAR2(255) DEFAULT NULL,
--     type               VARCHAR2(100) DEFAULT NULL,
--     time_interval      NUMBER,
--     interval_threshold NUMBER,
--     single_threshold   NUMBER,
--     status             CHAR(1)   DEFAULT '1',
--     create_time        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     update_time        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     PRIMARY KEY (id)
-- );
--
-- CREATE TABLE access_control_frequency
-- (
--     id            VARCHAR2(100) NOT NULL,
--     service_key   VARCHAR2(255) DEFAULT NULL,
--     type          VARCHAR2(100) DEFAULT NULL,
--     time_interval NUMBER,
--     threshold     NUMBER,
--     status        CHAR(1)   DEFAULT '1',
--     create_time   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     update_time   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     PRIMARY KEY (id)
-- );
--
-- -- flow_meter_catcher
--
-- CREATE TABLE flow_meter_catcher
-- (
--     moment            DATE DEFAULT NULL,
--     uuid              VARCHAR2(50) DEFAULT NULL,
--     pid               VARCHAR2(20) DEFAULT NULL,
--     father_pid        VARCHAR2(20) DEFAULT NULL,
--     root_pid          VARCHAR2(20) DEFAULT NULL,
--     system_pid        NUMBER    DEFAULT NULL,
--     project           VARCHAR2(50) DEFAULT NULL,
--     job               VARCHAR2(255) DEFAULT NULL,
--     job_repository_id VARCHAR2(255) DEFAULT NULL,
--     job_version       VARCHAR2(255) DEFAULT NULL,
--     context           VARCHAR2(50) DEFAULT NULL,
--     origin            VARCHAR2(255) DEFAULT NULL,
--     label             VARCHAR2(255) DEFAULT NULL,
--     count             NUMBER    DEFAULT NULL,
--     reference         NUMBER    DEFAULT NULL,
--     thresholds        VARCHAR2(255) DEFAULT NULL
-- );
--
-- -- log_catcher
--
-- CREATE TABLE log_catcher
-- (
--     moment     DATE DEFAULT NULL,
--     uuid       VARCHAR2(50) DEFAULT NULL,
--     pid        VARCHAR2(20) DEFAULT NULL,
--     root_pid   VARCHAR2(20) DEFAULT NULL,
--     father_pid VARCHAR2(20) DEFAULT NULL,
--     project    VARCHAR2(50) DEFAULT NULL,
--     job        VARCHAR2(255) DEFAULT NULL,
--     context    VARCHAR2(50) DEFAULT NULL,
--     priority   NUMBER    DEFAULT NULL,
--     type       VARCHAR2(255) DEFAULT NULL,
--     origin     VARCHAR2(255) DEFAULT NULL,
--     message    VARCHAR2(255) DEFAULT NULL,
--     code       NUMBER    DEFAULT NULL
-- );
--
-- -- stat_catcher
--
-- CREATE TABLE stat_catcher
-- (
--     moment            DATE DEFAULT NULL,
--     uuid              VARCHAR2(50) DEFAULT NULL,
--     pid               VARCHAR2(20) DEFAULT NULL,
--     father_pid        VARCHAR2(20) DEFAULT NULL,
--     root_pid          VARCHAR2(20) DEFAULT NULL,
--     system_pid        NUMBER    DEFAULT NULL,
--     project           VARCHAR2(50) DEFAULT NULL,
--     job               VARCHAR2(255) DEFAULT NULL,
--     job_repository_id VARCHAR2(255) DEFAULT NULL,
--     job_version       VARCHAR2(255) DEFAULT NULL,
--     context           VARCHAR2(50) DEFAULT NULL,
--     origin            VARCHAR2(255) DEFAULT NULL,
--     message_type      VARCHAR2(255) DEFAULT NULL,
--     message           VARCHAR2(255) DEFAULT NULL,
--     duration          NUMBER    DEFAULT NULL
-- );
