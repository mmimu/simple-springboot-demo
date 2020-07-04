package com.mimu.springboot.mybatis.generator.mapper.school;

import com.mimu.springboot.mybatis.generator.model.school.SchoolInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface SchoolInfoMapper {
    @Delete({
        "delete from school_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into school_info (serial, name, ",
        "address)",
        "values (#{serial,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{address,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SchoolInfo record);

    int insertSelective(SchoolInfo record);

    @Select({
        "select",
        "id, serial, name, address",
        "from school_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.mimu.springboot.mybatis.generator.mapper.school.SchoolInfoMapper.BaseResultMap")
    SchoolInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SchoolInfo record);

    @Update({
        "update school_info",
        "set serial = #{serial,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SchoolInfo record);
}