package com.irene.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.irene.Entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
