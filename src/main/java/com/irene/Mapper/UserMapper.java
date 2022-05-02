package com.irene.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.irene.Entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    public List<User> Findall();
}
