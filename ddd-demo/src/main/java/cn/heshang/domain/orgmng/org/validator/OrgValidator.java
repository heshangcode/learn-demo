package cn.heshang.domain.orgmng.org.validator;

import cn.heshang.application.orgmng.OrgResponse;
import cn.heshang.domain.common.CommonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/1/16 09:21
 * version: 1.0
 */

@Component
public class OrgValidator {
    //依赖更小的 Validator，也就是更小的规则分组
    private final CommonValidator commonValidator;
    private final OrgTypeValidator orgTypeValidator;
    private final SuperiorValidator superiorValidator;
    private final OrgNameValidator orgNameValidator;
    private final OrgLeaderValidator orgLeaderValidator;

    @Autowired
    public OrgValidator(CommonValidator commonValidator
            , OrgTypeValidator orgTypeValidator
            , SuperiorValidator superiorValidator
            , OrgNameValidator orgNameValidator
            , OrgLeaderValidator) {
        // 为依赖注入的组件赋值...
    }

    public void validate(OrgResponse request) {
        final Long tenant = request.getTenant();

        commonValidator.tenantShouldValid(tenant);
        orgLeaderValidator.verfy(tenant, request.getLeader;
        orgTypeValidator.verify(tenant, request.getOrgType());
        superiorValidator.verify(tenant, request.getSuperior()
                , request.getOrgType());
        orgNameValidator.verify(tenant, request.getName()
                , request.getSuperior());
    }
}