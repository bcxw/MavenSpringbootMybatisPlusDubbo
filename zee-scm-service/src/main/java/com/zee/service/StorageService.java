package com.zee.service;

import java.util.Map;

/**
 * 仓库模块服务
 */
public interface StorageService {

    /**
     * 新增/修改
     *
     * @param paramMap
     * @return
     */
    Map<String, Object> save(Map<String, Object> paramMap);

    /**
     * 修改仓库状态
     *
     * @param paramMap
     * @return
     */
    Map<String, Object> changeStatus(Map<String, Object> paramMap);



    /**
     * 多条件查询
     *
     * @param paramMap
     * @return
     */
    Map<String, Object> findByCondition(Map<String, Object> paramMap);

}
