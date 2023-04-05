package cn.heshang.domain.orgmng.emp;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/2/7 08:57
 * version: 1.0
 */

import cn.heshang.common.framework.domain.AuditableEntity;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
// imports ...

@Data
public class WorkExperience extends AuditableEntity {
    private Long id;              // 只读
    private Long tenantId;        // 只读
    private Period period;
    private String company;       // 读写

    // 包级私有权限
    protected WorkExperience(Long tenantId, Period period, LocalDateTime createdAt, Long createdBy) {
        super(createdAt, createdBy);
        this.tenantId = tenantId;
        this.period = period;
    }

    // setters and getters ...

    // 包级私有权限
    WorkExperience setCompany(String company) {
        this.company = company;
        return this;
    }

}
