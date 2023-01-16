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
    private final OrgValidator orgValidator;

    // "添加组织"功能的入口
    public OrgDto addOrg(OrgDto request, Long userId) {
        validate(request);
        Org org = buildOrg(request, userId);
        org = orgRepository.save(org);
        return buildOrgDto(org);
    }

    private OrgDto buildOrgDto(Org org) {
        //将领域对象转成DTO...
    }

    private Org buildOrg(OrgDto request, Long userId) {
        //将DTO转成领域对象...
    }

    //把各业务规则抽成了方法
    private void validate(OrgDto request) {
        orgValidator.validate(request);
    }

}
