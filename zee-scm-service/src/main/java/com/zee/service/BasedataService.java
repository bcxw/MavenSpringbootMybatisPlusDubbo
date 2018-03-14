package com.zee.service;

import java.util.Map;

/**
 * 基础数据模块服务
 */
public interface BasedataService {
    /**
     * 保存
     * @param paramMap
     * @return
     */
    Map<String, Object> save(Map<String,Object> paramMap);

    /**
     * 根据父类id查询
     * @param parentId
     * @return
     */
    Map<String, Object> findByParentId(String parentId);

    /**
     * 删除
     * @param id
     * @return
     */
    Map<String, Object> removeById(String id);

    /**
     * 根据父类code查询
     * @param parentCode
     * @return
     */
    Map<String, Object> getBaseDataByParentCode(String parentCode);

}
