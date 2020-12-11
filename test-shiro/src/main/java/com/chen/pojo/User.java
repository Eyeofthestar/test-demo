package com.chen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 *  用户实体类
 */

@Data
@AllArgsConstructor
public class User {
    String id;
    String userName;
    String password;

    /*
    *  用户对应角色集合
    * */
    private Set<Role> roles;
}
