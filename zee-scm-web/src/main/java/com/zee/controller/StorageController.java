package com.zee.controller;

import com.zee.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 仓库管理接口
 */
@RestController
@RequestMapping("/storage")
public class StorageController {
    @Autowired
    private StorageService storageService;

    /**
     * 根据条件查询展示仓库信息
     *
     * @param startIndex
     * @param pageSize
     * @param paramMap
     * @return
     */

    @GetMapping("/list")
    public Map<String, Object> List(@RequestParam(value = "start", defaultValue = "0") Integer startIndex,
                                    @RequestParam(value = "limit", defaultValue = "5") Integer pageSize,
                                    @RequestParam Map<String, Object> paramMap) {

        paramMap.put("startIndex", startIndex);
        paramMap.put("pageSize", pageSize);
        return storageService.findByCondition(paramMap);
    }

    /**
     * 改变仓库状态
     *
     * @param
     * @return
     */

    @GetMapping("/change")
    public Map<String, Object> changeStatus(@RequestParam Map<String, Object> paramMap) {

        return storageService.changeStatus(paramMap);
    }


    /**
     * 新增,修改仓库信息
     *
     * @param paramMap
     * @return
     */

    @PostMapping("/save")
    public Map<String, Object> save(@RequestParam Map<String, Object> paramMap) {

        return storageService.save(paramMap);
    }

}
