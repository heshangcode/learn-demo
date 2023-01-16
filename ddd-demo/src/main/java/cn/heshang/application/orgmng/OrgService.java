package cn.heshang.application.orgmng;

import cn.heshang.adapter.driven.resetful.orgmng.OrgDto;
import cn.heshang.adapter.driving.persistence.orgmng.EmpRepository;
import cn.heshang.adapter.driving.persistence.orgmng.OrgRepository;
import cn.heshang.adapter.driving.persistence.orgmng.OrgTypeRepository;
import cn.heshang.adapter.driving.persistence.tenantmng.TenantRepository;
import cn.heshang.adapter.driving.persistence.usermng.UserRepository;
import cn.heshang.domain.orgmng.Org;
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
    private final UserRepository userRepository;
    private final TenantRepository tenantRepository;
    private final OrgTypeRepository orgTypeRepository;
    private final OrgRepository orgRepository;
    private final EmpRepository empRepository;


    @Autowired
    public OrgService(UserRepository userRepository, TenantRepository tenantRepository, OrgTypeRepository orgTypeRepository, OrgRepository orgRepository, EmpRepository empRepository) {
        this.userRepository = userRepository;
        this.tenantRepository = tenantRepository;
        this.orgTypeRepository = orgTypeRepository;
        this.orgRepository = orgRepository;
        this.empRepository = empRepository;
    }

    public OrgDto addOrg(OrgDto request, String userId) {
        validate(request, userId);
        Org org = buildOrg(request, userId);
        org = orgRepository.save(org);
        return buildOrgDto(org);
    }

    private OrgDto buildOrgDto(Org org) {
        // 将领域对象的值赋给DTO...
    }

    private Org buildOrg(OrgDto request, String useId) {
        // 将DTO的值赋给领域对象...
    }

    private void validate(OrgDto request, String userId) {
        //进行各种业务规则的校验，会用到上面的各个Repository...
    }
}
