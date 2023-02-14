package cn.heshang.domain.orgmng.org;

import cn.heshang.application.orgmng.orgservice.OrgResponse;
import cn.heshang.domain.orgmng.org.validator.OrgValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/1/16 09:28
 * version: 1.0
 */

@Component
public class OrgFactory {
    private final OrgValidator validator;

    @Autowired
    public OrgFactory(OrgValidator validator) {
        this.validator = validator;
    }

    public Org build(OrgResponse request, Long userId) {
        validator.validate(request);
        return buildOrg(request);
    }

    private Org buildOrg(OrgResponse request) {
        //DTO转换成领域对象
    }
}