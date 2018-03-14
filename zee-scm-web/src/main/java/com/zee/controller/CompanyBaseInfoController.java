package com.zee.controller;

import com.zee.service.CompanyBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 公司基本信息维护接口
 */
@Controller
@RequestMapping("/companybaseinfo")
public class CompanyBaseInfoController {
    @Autowired
    private CompanyBaseInfoService companyBaseInfoService;

    /**
     * 根据条件查询展示公司信息,默认展示全部
     *
     * @param startIndex
     * @param pageSize
     * @param paramMap
     * @return
     */
    @ResponseBody
    @GetMapping("/list")
    public Map<String, Object> List(@RequestParam(value = "start", defaultValue = "0") Integer startIndex,
                                    @RequestParam(value = "limit", defaultValue = "5") Integer pageSize,
                                    @RequestParam Map<String, Object> paramMap) {

        paramMap.put("startIndex", startIndex);
        paramMap.put("pageSize", pageSize);
        return companyBaseInfoService.findByCondition(paramMap);
    }

    /**
     * 根据id查询公司信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/detail")
    public Map<String, Object> findById(@RequestParam("id") String id) {

        return companyBaseInfoService.findById(id);
    }

    /**
     * 根据id删除公司信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/remove")
    public Map<String, Object> removeById(@RequestParam("id") String id) {
        return companyBaseInfoService.removeById(id);
    }

    /**
     * 新增,修改公司信息
     *
     * @param paramMap
     * @return
     */
    @ResponseBody
    @PostMapping("/save")
    public Map<String, Object> save(@RequestParam Map<String, Object> paramMap) {
        return companyBaseInfoService.save(paramMap);
    }
}
