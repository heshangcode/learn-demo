package cn.heshang.application.orgmng;

import cn.heshang.domain.orgmng.org.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private final OrgBuilderFactory orgBuilderFactory;

    @Autowired
    private final OrgHandler orgHandler;

    // "添加组织"功能的入口
    @Transactional
    public OrgResponse addOrg(OrgResponse request, Long userId) {
        OrgBuilder orgBuilder = orgBuilderFactory.create();
        //修改的部分在这里
        //Org org = orgBuilder.tenantId(request.getTenantId()).orgTypeCode(request.getOrgTypeCode()).leaderId(request.getLeaderId()).superiorId(request.getSuperiorId()).name(request.getName()).createdBy(userid).build();
        Org org = orgBuilder.tenantId(request.getTenantId()).build();

        //包含校验逻辑在内的创建逻辑都委托给了工厂
        //Org org = orgFactory.build(request, userId);

        org = orgRepository.save(org);
        return buildOrgDto(org);
    }


    //修改组织基本信息
    @Transactional
    public OrgResponse updateOrgBasic(Long id, OrgResponse request, Long userId) {
        Org org = orgRepository.findById(request.getTenant(), id).orElseThrow(() -> {
            throw new BusinessException("要修改的组织(id =" + id + " )不存在！");
        });
        orgHandler.updateBasic(org, request.getName(), request.getLeader(), userId);
        orgRepository.update(org);
        return buildOrgDto(org);
    }

    //取消组织
    @Transactional
    public Long cancelOrg(Long id, Long tenant, Long userId) {
        Org org = orgRepository.findById(tenant, id).orElseThrow(() -> {
            throw new BusinessException("要取消的组织(id =" + id + "  )不存在！");
        });
        orgHandler.cancel(org, userId);
        orgRepository.update(org);
        return org.getId();
    }


    private OrgResponse buildOrgDto(Org org) {
        //将领域对象转成DTO...
    }

    //原来DTO转领域对象的逻辑也移到了工厂


}
