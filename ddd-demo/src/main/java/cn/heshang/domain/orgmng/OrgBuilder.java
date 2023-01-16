package cn.heshang.domain.orgmng;

import cn.heshang.domain.common.CommonValidator;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/1/16 09:33
 * version: 1.0
 */
public class OrgBuilder {
    //用到的 validator
    private final CommonValidator commonValidator;
    private final OrgTypeValidator orgTypeValidator;
    private final SuperiorValidator superiorValidator;
    private final OrgNameValidator orgNameValidator;
    private final OrgLeaderValidator orgLeaderValidator;

    //用这些属性保存创建对象用到的参数
    private Long tenantId;
    private Long superiorId;
    private String orgTypeCode;
    private Long leaderId;
    private String name;
    private Long createdBy;

    public OrgBuilder(CommonValidator commonValidator, OrgTypeValidator orgTypeValidator, SuperiorValidator superiorValidator, OrgNameValidator orgNameValidator, OrgLeaderValidator orgLeaderValidator) {
        this.commonValidator = commonValidator;
        this.orgTypeValidator = orgTypeValidator;
        this.superiorValidator = superiorValidator;
        this.orgNameValidator = orgNameValidator;
        this.orgLeaderValidator = orgLeaderValidator;
    }

    public OrgBuilder tenantId(Long tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public Org build() {
        validate();
        Org org = new Org();
        org.setOrgTypeCode(this.orgTypeCode);
        org.setLeaderId(this.leaderId);
        org.setName(this.name);
        org.setSuperiorId(this.superiorId);
        org.setTenantId(this.tenantId);
        org.setCreatedBy(this.createdBy);
        org.setCreatedAt(LocalDateTime.now());
        return org;
    }

    private void validate() {
        commonValidator.tenantShouldValid(tenantId);
        orgTypeValidator.verify(tenantId, orgTypeCode);
        superiorValidator.verify(tenantId, superiorId, orgTypeCode);
        orgLeaderValidator.verify(tenantId, leaderId);
        orgNameValidator.verify(tenantId, name, superiorId);
    }
}
}
