package com.irene.ServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.irene.Entity.Comment;
import com.irene.Mapper.CommentMapper;
import com.irene.Service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
}
