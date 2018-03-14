package com.zee.service;

import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


public class BasedataServiceTest extends BaseTest {

    @Resource
    private BasedataService basedataService;

    @Test
    public void saveTest(){
        Map<String,Object> paramMap =new HashMap<String, Object>();
        paramMap.put("name","houyong Test ddddd");
        paramMap.put("parentId","0");
        Map<String,Object> result=basedataService.save(paramMap);
        System.out.println(result.toString());

    }

}
