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

    //主要的领域逻辑在这个方法
    private void validate(OrgDto request) {
        final var tenant = request.getTenant();

        // 租户必须有效
        if (!tenantRepository.existsByIdAndStatus(tenant, TenantStatus.EFFECTIVE)) {
            throw new BusinessException("id为'" + tenant
                    + "'的租户不是有效租户！");
        }

        // 组织类别不能为空
        if (isBlank(request.getOrgType())) {
            throw new BusinessException("组织类别不能为空！");
        }

        // 企业是在创建租户的时候创建好的，因此不能单独创建企业
        if ("ENTP".equals(request.getOrgType())) {
            throw new BusinessException("企业是在创建租户的时候创建好的，因此不能单独创建企业!");
        }

        // 组织类别必须有效
        if (!orgTypeRepository.existsByCodeAndStatus(tenant, request.getOrgType(), OrgTypeStatus.EFFECTIVE)) {
            throw new BusinessException("'" + request.getOrgType()
                    + "'不是有效的组织类别代码！");
        }


        // 上级组织应该是有效组织
        Org superior = orgRepository.findByIdAndStatus(tenant
                        , request.getSuperior(), OrgStatus.EFFECTIVE)
                .orElseThrow(() ->
                        new BusinessException("'" + request.getSuperior()
                                + "' 不是有效的组织 id !"));


        // 取上级组织的组织类别
        OrgType superiorOrgType = orgTypeRepository.findByCodeAndStatus(tenant
                        , superior.getOrgType()
                        , OrgTypeStatus.EFFECTIVE)
                .orElseThrow(() ->
                        new DirtyDataException("id 为 '"
                                + request.getSuperior()
                                + "' 的组织的组织类型代码 '"
                                + superior.getOrgType() + "' 无效!"));

        // 开发组的上级只能是开发中心
        if ("DEVGRP".equals(request.getOrgType()) && !"DEVCENT".equals(superiorOrgType.getCode())) {
            throw new BusinessException("开发组的上级(id = '"
                    + request.getSuperior() + "')不是开发中心！");
        }

        // 开发中心和直属部门的上级只能是企业
        if (("DEVCENT".equals(request.getOrgType()) || "DIRDEP".equals(request.getOrgType()))
                && !"ENTP".equals(superiorOrgType.getCode())) {
            throw new BusinessException("开发中心或直属部门的上级(id = '"
                    + request.getSuperior() + "')不是企业！");
        }


        // 组织负责人可以空缺，如果有的话，的必须是一个在职员工（含试用期）
        if (request.getLeader() != null
                && !empRepository.existsByIdAndStatus(tenant, request.getLeader()
                , EmpStatus.REGULAR, EmpStatus.PROBATION)) {
            throw new BusinessException("组织负责人(id='"
                    + request.getLeader() + "')不是在职员工！");
        }

        // 组织必须有名称
        if (isBlank(request.getName())) {
            throw new BusinessException("组织没有名称！");
        }

        // 同一个组织下的下级组织不能重名
        if (orgRepository.existsBySuperiorAndName(tenant, request.getSuperior(), request.getName())) {
            throw new BusinessException("同一上级下已经有名为'"
                    + request.getName() + "'的组织存在！");
        }
    }
}
