package com.irene.ServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.irene.Entity.UserInfo;
import com.irene.Mapper.UserInfoMapper;
import com.irene.Service.UserInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserInfoImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
}
