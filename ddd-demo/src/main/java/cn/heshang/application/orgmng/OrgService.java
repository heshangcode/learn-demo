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

        final Long tenant = request.getTenant();

        tenantShouldValid(tenant);

        leaderShouldBeEffective(tenant, request.getLeader());

        //为了避免这个方法太长，把一些规则进一步分了组
        verifyOrgType(tenant, request.getOrgType());
        validateSuperior(tenant, request.getSuperior(), request.getOrgType());
        verifyOrgName(tenant, request.getName(), request.getSuperior());
    }

    // 租户必须有效
    private void tenantShouldValid(Long tenant) {
        //...
    }

    // 组织负责人可以空缺，如果有的话，的必须是一个在职员工（含试用期）
    private void leaderShouldBeEffective(Long tenant, Long leader) {
        //...
    }

    //校验组织类别的规则分组
    private void verifyOrgType(Long tenant, String orgType) {
        orgTypeShouldNotEmpty(orgType);
        orgTypeShouldBeValid(tenant, orgType);
        shouldNotCreateEntpAlone(orgType);
    }

    // 组织类别不能为空
    private void orgTypeShouldNotEmpty(String orgType) {
        //...
    }

    // 组织类别必须有效
    private void orgTypeShouldBeValid(Long tenant, String orgType) {
        //...
    }

    // 企业是在创建租户的时候创建好的，因此不能单独创建企业
    private void shouldNotCreateEntpAlone(String orgType) {
        //...
    }


    //校验上级组织的规则分组
    private void validateSuperior(Long tenant, Long superior, String orgType) {
        Org superiorOrg = superiorShouldEffective(tenant, superior);
        OrgType superiorOrgType = findSuperiorOrgType(tenant, superior, superiorOrg);
        superiorOfDevGroupMustDevCenter(superior, orgType
                , superiorOrgType);
        SuperiorOfDevCenterAndDirectDeptMustEntp(superior, orgType
                , superiorOrgType);
    }

    // 上级组织应该是有效
    private Org superiorShouldEffective(Long tenant, Long superior) {
        //...
    }

    private OrgType findSuperiorOrgType(Long tenant, Long superior, Org superiorOrg) {
        //...
    }

    // 开发中心和直属部门的上级只能是企业
    private void SuperiorOfDevCenterAndDirectDeptMustEntp(Long superior, String orgType, OrgType superiorOrgType) {
        //...
    }

    // 开发组的上级只能是开发中心
    private void superiorOfDevGroupMustDevCenter(Long superior, String orgType, OrgType superiorOrgType) {
        //...
    }

    // 校验组织名称的规则分组
    private void verifyOrgName(Long tenant, String name, Long superior) {
        orgNameShouldNotEmpty(name);
        nameShouldNotDuplicatedInSameSuperior(tenant, superior, name);
    }

    // 组织必须有名称
    private void orgNameShouldNotEmpty(String name) {
        //...
    }

    // 同一个上级下的组织不能重名
    private void nameShouldNotDuplicatedInSameSuperior(Long tenant, Long superior, String name) {
        //...
    }
}
