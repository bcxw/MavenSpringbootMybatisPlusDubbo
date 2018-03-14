package com.zee.service;

import java.util.Map;

/**
 * 公司信息模块服务
 */
public interface CompanyBaseInfoService {
    /**
     * 新增/修改
     *
     * @param paramMap
     * @return
     */
    Map<String, Object>
    save(Map<String, Object> paramMap);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    Map<String, Object> removeById(String id);

    /**
     * 多条件查询
     *
     * @param conditonMap
     * @return
     */
    Map<String, Object> findByCondition(Map<String, Object> conditonMap);

    /**
     * 根據id查詢公司信息
     *
     * @param id
     * @return
     */
    Map<String, Object> findById(String id);
}

