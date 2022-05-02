package com.irene.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.irene.Entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
