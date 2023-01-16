package cn.heshang.domain.orgmng;

import cn.heshang.application.orgmng.OrgDto;
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

    public Org build(OrgDto request, Long userId) {
        validator.validate(request);
        return buildOrg(request);
    }

    private Org buildOrg(OrgDto request) {
        //DTO转换成领域对象
    }
}