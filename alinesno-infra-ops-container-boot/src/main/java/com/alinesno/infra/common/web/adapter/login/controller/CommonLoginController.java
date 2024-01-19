package com.alinesno.infra.common.web.adapter.login.controller;

import com.alinesno.infra.common.facade.response.AjaxResult;
import com.alinesno.infra.common.web.adapter.dto.LoginBodyDto;
import com.alinesno.infra.common.web.adapter.dto.menus.Menu;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class CommonLoginController {

    /**
     * 令牌
     */
    public static final String TOKEN = "token";

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBodyDto loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = UUID.randomUUID().toString() ;
        ajax.put(TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo() {

        Map<String, Object> data = new HashMap<>();
        // 将数据填充到data中...
        data.put("permissions", new String[]{"*:*:*"});

        Map<String, Object> user = new HashMap<>();
        user.put("createBy", "admin");
        user.put("createTime", "2023-04-23 16:11:38");
        user.put("updateBy", null);
        user.put("updateTime", null);
        user.put("remark", "管理员");
        user.put("userId", 1);
        user.put("deptId", 103);
        user.put("userName", "admin");
        user.put("nickName", "AIP技术团队");
        user.put("email", "aip-team@163.com");
        user.put("phonenumber", "15888888888");
        user.put("sex", "1");
        user.put("avatar", "");
        user.put("password", "");
        user.put("status", "0");
        user.put("delFlag", "0");
        user.put("loginIp", "");
        user.put("loginDate", "2023-09-21T16:54:12.000+08:00");

        Map<String, Object> dept = new HashMap<>();
        dept.put("createBy", null);
        dept.put("createTime", null);
        dept.put("updateBy", null);
        dept.put("updateTime", null);
        dept.put("remark", null);
        dept.put("deptId", 103);
        dept.put("parentId", 101);
        dept.put("ancestors", "0,100,101");
        dept.put("deptName", "研发部门");
        dept.put("orderNum", 1);
        dept.put("leader", "AIP技术团队");
        dept.put("phone", null);
        dept.put("email", null);
        dept.put("status", "0");
        dept.put("delFlag", null);
        dept.put("parentName", null);
        dept.put("children", new Object[]{});

        user.put("dept", dept);

        Map<String, Object> role = new HashMap<>();
        role.put("createBy", null);
        role.put("createTime", null);
        role.put("updateBy", null);
        role.put("updateTime", null);
        role.put("remark", null);
        role.put("roleId", 1);
        role.put("roleName", "超级管理员");
        role.put("roleKey", "admin");
        role.put("roleSort", 1);
        role.put("dataScope", "1");
        role.put("menuCheckStrictly", false);
        role.put("deptCheckStrictly", false);
        role.put("status", "0");
        role.put("delFlag", null);
        role.put("flag", false);
        role.put("menuIds", null);
        role.put("deptIds", null);
        role.put("permissions", null);
        role.put("admin", true);

        user.put("roles", new Object[]{role});

        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", user.get("roles"));
        ajax.put("permissions", data.get("permissions"));

        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {

        Menu dashboardMenu = new Menu("Dashboard", "/dashboard", false, "noRedirect", "Layout", true, new Menu.Meta("仪盘表", "dashboard", false, null), List.of(
                new Menu("Dashboard", "index", false, false , "dashboard", new Menu.Meta("概览", "dashboard", false, null))
        ));

        Menu systemMenu = new Menu("Cluster", "/cluster", false, "noRedirect", "Layout", true, new Menu.Meta("集群管理", "post", false, null),
                List.of(
                        new Menu("Node", "ops/container/nodes/index", false,false,  "ops/container/nodes/index", new Menu.Meta("节点管理", "tree", false, null)),
                        new Menu("Cluster", "ops/container/cluster/index", false,false,  "ops/container/cluster/index", new Menu.Meta("集群管理", "user", false, null))
                ));

        Menu serviceMenu = new Menu("Service", "/service", false, "noRedirect", "Layout", true, new Menu.Meta("服务管理", "log", false, null),
                        List.of(
                                new Menu("Service", "ops/container/service/index", false,false, "ops/container/service/index", new Menu.Meta("服务管理", "form", false, null)),
                                new Menu("Deployment", "ops/container/deployment/index", false,false, "ops/container/deployment/index", new Menu.Meta("部署管理", "logininfor", false, null)),
                                new Menu("Plugin", "ops/container/plugins/index", false,false, "ops/container/plugins/index", new Menu.Meta("插件管理", "form", false, null))
                        ));

        Menu monitorMenu = new Menu("Pod", "/pod", false, "noRedirect", "Layout", true, new Menu.Meta("容器管理", "monitor", false, null),
                List.of(
                        new Menu("Online", "ops/container/pod/index", false,false, "ops/container/pod/index", new Menu.Meta("Pod管理", "online", false, null)),
                        new Menu("Job", "ops/container/ingress/index", false,false, "ops/container/ingress/index", new Menu.Meta("应用路由", "job", false, null)),
                        new Menu("ConfigMap", "ops/container/configMap/index", false,false, "ops/container/configMap/index", new Menu.Meta("配置字典", "server", false, null)),
                        new Menu("Secret", "ops/container/secret/index", false,false, "ops/container/secret/index", new Menu.Meta("密文", "redis", false, null))
                ));

        Menu themeMenu = new Menu("Storage", "/storage", false, "noRedirect", "Layout", true, new Menu.Meta("存储管理", "tool", false, null),
                List.of(
                        new Menu("StorageClass", "ops/container/storageClass/index", false,false, "ops/container/storageClass/index", new Menu.Meta("存储类", "build", false, null)),
                        new Menu("Pv", "ops/container/pv/index",false, false, "ops/container/pv/index", new Menu.Meta("存储卷", "swagger", false, null))));

        Menu loggerMenu = new Menu("Logger", "/logger", false, "noRedirect", "Layout", true, new Menu.Meta("审计管理", "swagger", false, null),
                List.of(
                        new Menu("Event", "ops/container/event/index", false,false, "ops/container/event/index", new Menu.Meta("事件管理", "build", false, null)),
                        new Menu("OperationLog", "ops/container/operationLog/index",false, false, "ops/container/operationLog/index", new Menu.Meta("操作日志", "swagger", false, null))));

        List<Menu> menus = List.of(dashboardMenu , systemMenu, serviceMenu , monitorMenu, themeMenu, loggerMenu);

        return AjaxResult.success(menus) ;
    }
}