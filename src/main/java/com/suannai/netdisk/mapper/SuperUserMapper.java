package com.suannai.netdisk.mapper;

import com.suannai.netdisk.model.SuperUser;
import com.suannai.netdisk.model.SuperUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SuperUserMapper {
    long countByExample(SuperUserExample example);

    int deleteByExample(SuperUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SuperUser record);

    int insertSelective(SuperUser record);

    List<SuperUser> selectByExample(SuperUserExample example);

    SuperUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SuperUser record, @Param("example") SuperUserExample example);

    int updateByExample(@Param("record") SuperUser record, @Param("example") SuperUserExample example);

    int updateByPrimaryKeySelective(SuperUser record);

    int updateByPrimaryKey(SuperUser record);
}