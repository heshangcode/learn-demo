package cn.heshang.domain.orgmng.emp;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/2/7 08:56
 * version: 1.0
 */

import cn.heshang.common.framework.domain.AuditableEntity;

import java.time.LocalDateTime;



// 这里采用了员工和技能、工作经验的对象单关联
public class Skill extends AuditableEntity {
    private Long id;            // 只读
    private Long tenantId;      // 只读
    private Long skillTypeId;   // 只读，表示到技能类型的ID关联
    SkillLevel level;           // 读写
    private int duration;       // 读写

    // 包级私有权限
    Skill(Long tenantId, Long skillTypeId, LocalDateTime createdAt, Long createdBy) {
        super(createdAt, createdBy);
        this.tenantId = tenantId;
        this.skillTypeId = skillTypeId;
    }

    public Long getId() {
        return id;
    }

    public Long getSkillTypeId() {
        return skillTypeId;
    }

    public SkillLevel getLevel() {
        return level;
    }

    // 包级私有权限
    void setLevel(SkillLevel level) {
        this.level = level;
    }

    public int getDuration() {
        return duration;
    }

    // 包级私有权限
    void setDuration(int duration) {
        this.duration = duration;
    }
}
