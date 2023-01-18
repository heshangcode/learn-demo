package cn.heshang.domain.orgmng.org;

import cn.heshang.common.framework.domain.AuditableEntity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/1/6 08:58
 * version: 1.0
 */
@Data
public class Org extends AuditableEntity {
    private Long id;
    private Long tenantId;
    private Long superiorId;
    private String orgTypeCode;
    private Long leaderId;
    private String name;
    private OrgStatus status;          // 使用了枚举类型

    public Org() {
        status = OrgStatus.EFFECTIVE;  //组织的初始状态默认为有效
    }

    //Org 自己管理自己的状态
    public void cancel() {
        this.status = OrgStatus.CANCELLED;
    }

    public boolean isEffective() {
        return status.equals(OrgStatus.EFFECTIVE);
    }
}
