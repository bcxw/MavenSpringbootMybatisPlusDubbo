package com.zee.serviceImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zee.common.ResultUtil;
import com.zee.dao.BasedataDao;
import com.zee.model.Basedata;
import com.zee.service.BasedataService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BasedataServiceImpl implements BasedataService {


    @Resource
    private BasedataDao basedataDao;

    @Override
    public Map<String, Object> getBaseDataByParentCode(String parentCode) {
        Basedata parentBasedata=new Basedata();
        parentBasedata.setCode(parentCode);
        parentBasedata=basedataDao.selectOne(parentBasedata);
        List<Basedata> list=basedataDao.selectList(new EntityWrapper<Basedata>().where("parent_id={0}",parentBasedata.getId()));
        return ResultUtil.success(list);
    }

    @Override
    public Map<String, Object> removeById(String id) {
        int chidCount=basedataDao.selectCount(new EntityWrapper<Basedata>().where("parent_id={0}",id));
        if(chidCount<=0){
            basedataDao.deleteById(id);
            return ResultUtil.success();
        }else{
            return ResultUtil.failure("此分类包含子类，不能删除！");
        }

    }

    @Override
    public Map<String, Object> save(Map<String,Object> paramMap) {
        Basedata basedata  = (Basedata)JSON.parseObject(JSON.toJSONString(paramMap), Basedata.class);
        if(StringUtils.isEmpty(paramMap.get("id"))){
            EntityWrapper<Basedata> ew=new EntityWrapper<Basedata>();
            ew.where("code={0}",basedata.getCode());
            List<Basedata> list=basedataDao.selectList(ew);
            if(list!=null&&!list.isEmpty()){
                return ResultUtil.failure("编码已经存在");
            }else{
                basedata.insert();
            }

        }else{
            EntityWrapper<Basedata> ew=new EntityWrapper<Basedata>();
            ew.where("code={0}",basedata.getCode());
            ew.and("id!={0}",basedata.getId());
            List<Basedata> list=basedataDao.selectList(ew);
            if(list!=null&&!list.isEmpty()){
                return ResultUtil.failure("编码已经存在");
            }else{
                basedataDao.updateById(basedata);
            }
        }
        return ResultUtil.success();
    }

    @Override
    public Map<String, Object> findByParentId(String parentId) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("parent_id",parentId);
        return ResultUtil.success(basedataDao.selectByMap(map));
    }
}
