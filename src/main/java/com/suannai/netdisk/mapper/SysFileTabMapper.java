package com.suannai.netdisk.mapper;

import com.suannai.netdisk.model.SysFileTab;
import com.suannai.netdisk.model.SysFileTabExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface SysFileTabMapper {
    long countByExample(SysFileTabExample example);

    int deleteByExample(SysFileTabExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysFileTab record);

    int insertSelective(SysFileTab record);

    List<SysFileTab> selectByExample(SysFileTabExample example);

    SysFileTab selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysFileTab record, @Param("example") SysFileTabExample example);

    int updateByExample(@Param("record") SysFileTab record, @Param("example") SysFileTabExample example);

    int updateByPrimaryKeySelective(SysFileTab record);

    int updateByPrimaryKey(SysFileTab record);
}