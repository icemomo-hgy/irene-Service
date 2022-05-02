package com.irene.ServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.irene.Entity.User;
import com.irene.Mapper.UserMapper;
import com.irene.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImpl extends ServiceImpl<UserMapper, User> implements UserService {


}
