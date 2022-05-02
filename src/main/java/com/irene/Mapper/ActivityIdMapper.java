package com.irene.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.irene.Entity.Activity;
import com.irene.Entity.ActivityId;
import com.irene.VO.ActivityDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActivityIdMapper extends BaseMapper<ActivityId> {
    public List<ActivityDetail> getServicesHistory(@Param("tokenid") Long tokenid);
}
