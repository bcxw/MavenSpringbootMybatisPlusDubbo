package com.zee.serviceImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zee.dao.BasedataDao;
import com.zee.model.Basedata;
import com.zee.service.SysUserService;
import com.zee.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Reference(version = "1.0.0")
    private SysUserService sysUserService;

    @Resource
    private BasedataDao basedataDao;

    @Override
    public boolean login(String userName, String password) {
        System.out.println("login sssssssssssssssssssssssssssss");
        System.out.println(sysUserService.getAll().size());
        System.out.println(sysUserService.getAll().get(0).getUsername());
        System.out.println("login eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

        System.out.println("basedata sssssssssssssssssssssssssssss");
        System.out.println(basedataDao.selectById(1).getName());
        System.out.println("basedata eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

        Basedata basedata=new Basedata();
        basedata.setId(9);
        basedata.setName("haha");
        basedata.insert();


        return false;
    }
}
