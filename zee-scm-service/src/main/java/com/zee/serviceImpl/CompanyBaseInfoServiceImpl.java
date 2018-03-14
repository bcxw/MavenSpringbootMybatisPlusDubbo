package com.zee.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zee.common.ResultUtil;
import com.zee.dao.CompanyBaseInfoDao;
import com.zee.dao.CompanyBrandDao;
import com.zee.model.Basedata;
import com.zee.model.CompanyBaseInfo;
import com.zee.model.CompanyBrand;
import com.zee.service.CompanyBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 公司信息服务实现类
 */
@Service
@Transactional
public class CompanyBaseInfoServiceImpl implements CompanyBaseInfoService {
    @Autowired
    private CompanyBaseInfoDao companybaseinfodao;
    @Autowired
    private CompanyBrandDao companyBrandDao;

    /**
     * 新增公司基本信息
     *
     * @param paramMap
     * @return
     */
    @Override
    public Map<String, Object> save(Map<String, Object> paramMap) {
        CompanyBaseInfo companyInfo = (CompanyBaseInfo) JSON.parseObject(JSON.toJSONString(paramMap), CompanyBaseInfo.class);
        List<CompanyBrand> companybrands = JSON.parseArray(JSON.toJSONString(paramMap.get("brands")), CompanyBrand.class);
        EntityWrapper<CompanyBaseInfo> ew = new EntityWrapper<CompanyBaseInfo>();
        ew.where("company_code={0}", companyInfo.getCompanyCode());
        //根据id是否存在判断进行新增还是修改
        String id = (String) paramMap.get("id");
        if (StringUtils.isEmpty(id)) {
            List<CompanyBaseInfo> list = companybaseinfodao.selectList(ew);
            if (list != null && !list.isEmpty()) {
                return ResultUtil.failure("公司编码已经存在");
            }
            companybaseinfodao.insert(companyInfo);
            id = companyInfo.getId();
            if (companybrands != null && !companybrands.isEmpty()) {

                for (CompanyBrand cb : companybrands) {
                    cb.setCompanyId(id);
                    cb.setId(UUID.randomUUID().toString().replace("-", ""));
                }
                companyBrandDao.saveList(companybrands);
            }
        } else {
            ew.and("id!={0}", companyInfo.getId());
            List<CompanyBaseInfo> list = companybaseinfodao.selectList(ew);
            if (list != null && !list.isEmpty()) {
                return ResultUtil.failure("公司编码已经存在");
            }
            companybaseinfodao.updateById(companyInfo);
            companyBrandDao.deleteByMap((Map<String, Object>) new HashMap<String, Object>().put("company_id", id));
            if (companybrands != null && !companybrands.isEmpty()) {
                companyBrandDao.saveList(companybrands);
            }


        }
        return ResultUtil.success("编辑成功");

    }

    /**
     * 根据id删除公司记录
     *
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> removeById(String id) {
        Integer row = companybaseinfodao.deleteById(id);

        if (row > 0) {
            companyBrandDao.deleteByMap((Map<String, Object>) new HashMap<String, Object>().put("company_id", id));
            return ResultUtil.success("删除成功!");
        } else {
            return ResultUtil.failure("删除失败!");
        }
    }

    /**
     * 根据条件查询公司信息
     *
     * @param conditionMap
     * @return
     */
    @Override
    public Map<String, Object> findByCondition(Map<String, Object> conditionMap) {
        List<CompanyBaseInfo> companyList = companybaseinfodao.finByCondition(conditionMap);
        int total = companybaseinfodao.getTotalByCondition(conditionMap);
        return ResultUtil.success(companyList, total);

    }

    /**
     * 根据id查询公司信息
     *
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> findById(String id) {
        CompanyBaseInfo companyBaseInfo = companybaseinfodao.selectById(id);
        if (companyBaseInfo != null) {
            EntityWrapper<CompanyBrand> ew = new EntityWrapper<CompanyBrand>();
            ew.where("company_id={0}", companyBaseInfo.getId());
            //根据公司id查询品牌
            List<CompanyBrand> companyBrands = companyBrandDao.selectList(ew);
            companyBaseInfo.setBrands(companyBrands);
        }
        return ResultUtil.success(companyBaseInfo);
    }


}
