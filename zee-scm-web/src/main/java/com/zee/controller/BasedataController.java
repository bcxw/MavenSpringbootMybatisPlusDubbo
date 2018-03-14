package com.zee.controller;

import com.zee.service.BasedataService;
import com.zee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 基础数据
 */

@RestController
@RequestMapping("/basedata")
public class BasedataController {

    @Autowired
    private BasedataService basedataService;

    /**
     * 保存基础数据
     * @param paramMap
     * @return
     */
    @RequestMapping("/save")
    public Map<String, Object> save(@RequestParam Map<String,Object> paramMap) {
        return basedataService.save(paramMap);
    }

    /**
     * 根据父类id查询下级数据
     * @param parentId
     * @return
     */
    @RequestMapping("/findByParentId")
    public Map<String, Object> findByParentId(String parentId) {
        return basedataService.findByParentId(parentId);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping("/removeById")
    public Map<String, Object> removeById(String id) {
        return basedataService.removeById(id);
    }

    /**
     * 根据父类编码获取下级数据，用于基础数据的公用
     * @param parentCode
     * @return
     */
    @RequestMapping("/getBaseDataByParentCode")
    public Map<String, Object> getBaseDataByParentCode(String parentCode) {
        return basedataService.getBaseDataByParentCode(parentCode);
    }

}
