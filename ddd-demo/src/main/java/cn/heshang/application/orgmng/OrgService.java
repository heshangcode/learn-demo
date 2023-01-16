package cn.heshang.application.orgmng;

import cn.heshang.domain.orgmng.*;
import cn.heshang.adapter.driving.persistence.tenantmng.TenantRepository;
import cn.heshang.adapter.driving.persistence.usermng.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/1/6 08:58
 * version: 1.0
 */
@Service
public class OrgService {
    @Autowired
    private final OrgRepository orgRepository;
    @Autowired
    private final OrgFactory orgFactory;

    // "添加组织"功能的入口
    public OrgDto addOrg(OrgDto request, Long userId) {
        //包含校验逻辑在内的创建逻辑都委托给了工厂
        Org org = orgFactory.build(request, userId);
        org = orgRepository.save(org);
        return buildOrgDto(org);
    }

    private OrgDto buildOrgDto(Org org) {
        //将领域对象转成DTO...
    }
    //原来DTO转领域对象的逻辑也移到了工厂

}
