package com.chen.realm;

import com.chen.pojo.Permissions;
import com.chen.pojo.Role;
import com.chen.pojo.User;
import com.chen.service.LoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 *  自定义Realm用于查询用户的角色和权限信息并保存到权限管理器：
 */

public class CustomRealm extends AuthorizingRealm {

    @Resource
    private LoginService loginService;

    /**
     * @MethodName doGetAuthorizationInfo
     * @Description 权限配置类
     * @Param [principalCollection]
     * @Return AuthorizationInfo
     * @Author WangShiLin
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        获取用户名
        String name= (String) principals.getPrimaryPrincipal();
//        更具用户名查询到用户
        User user=loginService.getUser(name);
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        for (Role role: user.getRoles()){
//            赋予角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            for (Permissions permission: role.getPermissions()){
//                赋予权限
                simpleAuthorizationInfo.addStringPermission(permission.getPermissionsName());
            }
        }

        return simpleAuthorizationInfo;
    }


    /**
     * @MethodName doGetAuthenticationInfo
     * @Description 认证配置类
     * @Param [authenticationToken]
     * @Return AuthenticationInfo
     * @Author WangShiLin
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (StringUtils.isEmpty(token.getPrincipal()))  return null;
        //获取用户信息
        String name = token.getPrincipal().toString();
        User user = loginService.getUser(name);
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.getPassword(), getName());
            return simpleAuthenticationInfo;
        }
    }
}
