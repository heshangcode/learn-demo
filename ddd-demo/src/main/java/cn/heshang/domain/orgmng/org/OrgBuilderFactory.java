package cn.heshang.domain.orgmng.org;

import cn.heshang.domain.common.CommonValidator;
import cn.heshang.domain.orgmng.org.validator.OrgTypeValidator;
import cn.heshang.domain.orgmng.org.validator.SuperiorValidator;
import cn.heshang.domain.orgmng.org.validator.OrgLeaderValidator;
import cn.heshang.domain.orgmng.org.validator.OrgNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/1/16 09:36
 * version: 1.0
 */


@Component
public class OrgBuilderFactory {
    @Autowired
    private final CommonValidator commonValidator;
    @Autowired
    private final OrgTypeValidator orgTypeValidator;
    @Autowired
    private final SuperiorValidator superiorValidator;
    @Autowired
    private final OrgNameValidator orgNameValidator;
    @Autowired
    private final OrgLeaderValidator orgLeaderValidator;

    //每次调用都创建一个新的 OrgBuilder
    public OrgBuilder create() {
        return new OrgBuilder(commonValidator
                , orgTypeValidator
                , superiorValidator
                , orgNameValidator
                , orgLeaderValidator);
    }
}
