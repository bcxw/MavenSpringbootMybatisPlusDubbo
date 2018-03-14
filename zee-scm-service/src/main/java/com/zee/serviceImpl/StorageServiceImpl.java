package com.zee.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zee.common.ResultUtil;
import com.zee.dao.StorageDao;
import com.zee.model.Storage;
import com.zee.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 仓库服务实现类
 */
@Service
@Transactional
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageDao storageDao;

    /**
     * 添加/修改
     *
     * @param paramMap
     * @return
     */
    @Override
    public Map<String, Object> save(Map<String, Object> paramMap) {
        Storage storage = JSON.parseObject(JSON.toJSONString(paramMap), Storage.class);
        EntityWrapper<Storage> ew = new EntityWrapper<Storage>();
        ew.where("storage_code={0}", storage.getStorageCode());
        if (StringUtils.isEmpty(paramMap.get("id"))) {

            List<Storage> list = storageDao.selectList(ew);
            if (list != null && !list.isEmpty()) {
                return ResultUtil.failure("编码已经存在");
            }
            storageDao.insert(storage);
        } else {
            ew.and("id!={0}", storage.getId());
            List<Storage> list = storageDao.selectList(ew);
            if (list != null && !list.isEmpty()) {
                return ResultUtil.failure("编码已经存在");
            }
            storageDao.updateById(storage);
        }
        return ResultUtil.success();
    }

    /**
     * 根据id改变仓库状态
     *
     * @param paramMap
     * @return
     */
    @Override
    public Map<String, Object> changeStatus(Map<String, Object> paramMap) {
        String status = "";
        if (paramMap.get("status").equals("0")) {
            status = "1";
            paramMap.put("status", status);
        }
        if (paramMap.get("status").equals("1")) {
            status = "0";
            paramMap.put("status", status);
        }
        storageDao.updateStatus(paramMap);
        return ResultUtil.success();
    }

    /**
     * 多条件查询
     *
     * @param paramMap
     * @return
     */
    @Override
    public Map<String, Object> findByCondition(Map<String, Object> paramMap) {
        List<Storage> storages = storageDao.finByCondition(paramMap);
        int total = storageDao.getTotalByCondition(paramMap);
        return ResultUtil.success(storages, total);
    }
}
