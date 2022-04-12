package com.suannai.netdisk.mapper;

import com.suannai.netdisk.model.TaskType;
import com.suannai.netdisk.model.TaskTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskTypeMapper {
    long countByExample(TaskTypeExample example);

    int deleteByExample(TaskTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaskType record);

    int insertSelective(TaskType record);

    List<TaskType> selectByExample(TaskTypeExample example);

    TaskType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TaskType record, @Param("example") TaskTypeExample example);

    int updateByExample(@Param("record") TaskType record, @Param("example") TaskTypeExample example);

    int updateByPrimaryKeySelective(TaskType record);

    int updateByPrimaryKey(TaskType record);
}