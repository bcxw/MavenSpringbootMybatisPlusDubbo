package com.zee.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zee.model.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * 仓库dao
 */
@Mapper
public interface StorageDao extends BaseMapper<Storage> {
    /**
     * 根据id更改状态
     *
     * @param paramMap
     * @return
     */
    @Update("update bd_storage set status =#{status} where id= #{id}")
    Integer updateStatus(Map<String, Object> paramMap);

    /**
     * 多条件动态查询
     *
     * @param paramMap
     * @return
     */
    @Select("<script>"
            + "	select * from bd_storage "
            + "   <where>"
            + "	 <if test='storageCode != null and storageCode != \"\"'>"
            + " 	and storage_code=#{storageCode} "
            + "	 </if>"
            + "	 <if test='storageName != null and storageName != \"\"'>"
            + " 	and storage_name like concat(concat('%',#{storageName},'%')) "
            + "	 </if>"
            + "	 <if test='storageType != null and storageType != \"\"'>"
            + " 	and storage_type = #{storageType} "
            + "	 </if>"
            + "	 <if test='status != null and status != \"\"'>"
            + " 	and status = #{status} "
            + "	 </if>"
            + "	  </where>"
            + "limit #{startIndex},#{pageSize} "
            + "</script>")
    List<Storage> finByCondition(Map<String, Object> paramMap);

    /**
     * 获得查询结果总条数
     *
     * @param paramMap
     * @return
     */
    @Select("<script>"
            + "	select count(1) from bd_storage "
            + "   <where>"
            + "	 <if test='storageCode != null and storageCode != \"\"'>"
            + " 	and storage_code=#{storageCode} "
            + "	 </if>"
            + "	 <if test='storageName != null and storageName != \"\"'>"
            + " 	and storage_name like concat(concat('%',#{storageName},'%')) "
            + "	 </if>"
            + "	 <if test='storageType != null and storageType != \"\"'>"
            + " 	and storage_type = #{storageType} "
            + "	 </if>"
            + "	 <if test='status != null and status != \"\"'>"
            + " 	and status = #{status} "
            + "	 </if>"
            + "	  </where>"
            + "limit #{startIndex},#{pageSize} "
            + "</script>")
    Integer getTotalByCondition(Map<String, Object> paramMap);
}
