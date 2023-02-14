package cn.heshang.domain.orgmng.org.validator;

import cn.heshang.domain.orgmng.org.Org;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/1/17 09:13
 * version: 1.0
 */

@Component
public class CancelOrgValidator {
    //...

    // 只有有效的组织才能被撤销
    public void OnlyEffectiveOrgCanBeCancelled(Org org) {
        //直接访问了状态属性
        if (!org.isEffective()) {
            throw new BusinessException("该组织不是有效状态，不能撤销！");
        }
    }

    //...
}
