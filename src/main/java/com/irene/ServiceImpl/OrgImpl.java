package com.irene.ServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.irene.Entity.Org;
import com.irene.Mapper.OrgMapper;
import com.irene.Service.OrgService;
import org.springframework.stereotype.Service;

@Service
public class OrgImpl extends ServiceImpl<OrgMapper, Org> implements OrgService {
}
