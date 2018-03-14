package com.zee.serviceImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zee.common.ResultUtil;
import com.zee.dao.BasedataDao;
import com.zee.dto.AclModuleLevelDto;
import com.zee.model.Basedata;
import com.zee.model.Response;
import com.zee.model.SysUser;
import com.zee.service.SysTreeService;
import com.zee.service.SysUserService;
import com.zee.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Reference(version = "1.0.0")
    private SysUserService sysUserService;

    @Reference(version = "1.0.0")
    private SysTreeService sysTreeService;

    @Value("${application.id}")
    private Integer applicationId;

    /**
     * 调用dubbo验证用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public Map<String, Object> login(String username, String password) {
        Response<SysUser> response=sysUserService.login(username,password);
        if(response.isSuccess()){
            Map<String, Object> userMap=new HashMap<String, Object>();
            userMap.put("id",response.getResult().getId());
            userMap.put("username",response.getResult().getUsername());
            return ResultUtil.success(userMap);
        }else{
            return ResultUtil.failure("用户名或密码错误");
        }
    }

    /**
     * 获取用户菜单
     *
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> getMenu(String id) {
        JSONArray jsonArr=JSON.parseArray(JSON.toJSONString(sysTreeService.userAclTreeByUserIdAndModuleId(applicationId,Integer.parseInt(id))));
        JSONObject jsonObj=jsonArr.getJSONObject(0);
        jsonArr=jsonObj.getJSONArray("aclModuleList");
        return ResultUtil.success(initMenuTree(jsonArr));
    }

    public JSONArray initMenuTree(JSONArray jsonArr){
        JSONArray returnJsonArr=new JSONArray();
        for(int i=0;i<jsonArr.size();i++){
            JSONObject returnJsonObj=new JSONObject();
            JSONObject jsonObj=jsonArr.getJSONObject(i);
            returnJsonObj.put("text",jsonObj.get("name"));
            returnJsonObj.put("sortorder",jsonObj.get("seq"));
            returnJsonObj.put("id",jsonObj.get("id"));

            JSONArray funJsonArr=jsonObj.getJSONArray("aclList");
            if(funJsonArr!=null&&!funJsonArr.isEmpty()){
                JSONObject authObj=new JSONObject();
                for(int f=0;f<funJsonArr.size();f++){
                    JSONObject funObj=funJsonArr.getJSONObject(f);
                    String url=funObj.getString("url");
                    if(url.contains("app.")){
                        returnJsonObj.put("uri",url);
                    }else{
                        authObj.put(url,true);
                    }
                }
                returnJsonObj.put("authData",authObj);
            }

            JSONArray childJsonArr=jsonObj.getJSONArray("aclModuleList");
            if(childJsonArr!=null&&!childJsonArr.isEmpty()){
                returnJsonObj.put("data",initMenuTree(childJsonArr));
            }else{
                returnJsonObj.put("leaf",true);
            }

            returnJsonArr.add(returnJsonObj);
        }
        return returnJsonArr;
    }

}
