package cn.heshang.domain.orgmng.org;

import cn.heshang.domain.common.CommonValidator;
import cn.heshang.domain.orgmng.org.validator.OrgLeaderValidator;
import cn.heshang.domain.orgmng.org.validator.OrgNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * OrgHandler 没有可变属性，因此可以直接注入到应用服务。
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/1/17 08:46
 * version: 1.0
 */
@Component
public class OrgHandler {
    @Autowired
    private final CommonValidator commonValidator;
    @Autowired
    private final OrgNameValidator nameValidator;
    @Autowired
    private final OrgLeaderValidator leaderValidator;
    @Autowired
    private CancelOrgValidator cancelValidator;

    public void updateBasic(Org org, String newName
            , Long newLeader, Long userId) {
        updateName(org, newName);
        updateLeader(org, newLeader);
        updateAuditInfo(org, userId);
    }

    public void cancel(Org org, Long userId) {
        cancelValidator.cancelledOrgShouldNotHasEmp(org.getTenant()
                , org.getId());
        cancelValidator.OnlyEffectiveOrgCanBeCancelled(org);
        org.setStatus(OrgStatus.CANCELLED);
        updateAuditInfo(org, userId);
    }

    private void updateLeader(Org org, Long newLeader) {
        if (newLeader != null && !newLeader.equals(org.getLeader())) {
            leaderValidator.leaderShouldBeEffective(org.getTenant()
                    , newLeader);
            org.setLeader(newLeader);
        }
    }

    private void updateName(Org org, String newName) {
        if (newName != null && !newName.equals(org.getName())) {
            nameValidator.orgNameShouldNotEmpty(newName);
            nameValidator.nameShouldNotDuplicatedInSameSuperior(
                    org.getTenant(), org.getSuperior(), newName);
            org.setName(newName);
        }
    }

    private void updateAuditInfo(Org org, Long userId) {
        // 设置最后修改人和时间
        // TODO: chenxinkui 2023/1/17 这里是否就是操作 db 了
    }
}
