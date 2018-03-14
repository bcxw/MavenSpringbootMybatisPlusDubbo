package com.zee.service;

import java.util.Map;

/**
 * 用户权限
 */
public interface UserService {
    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    Map<String, Object> login(String userName, String password);

    /**
     * 获取用户菜单
     * @param id
     * @return
     */
    Map<String, Object> getMenu(String id);

}
