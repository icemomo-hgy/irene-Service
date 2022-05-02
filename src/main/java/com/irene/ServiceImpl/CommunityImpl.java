package com.irene.ServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.irene.Entity.Community;
import com.irene.Mapper.CommunityMapper;
import com.irene.Service.CommunityService;
import org.springframework.stereotype.Service;

@Service
public class CommunityImpl extends ServiceImpl<CommunityMapper, Community> implements CommunityService {
}
