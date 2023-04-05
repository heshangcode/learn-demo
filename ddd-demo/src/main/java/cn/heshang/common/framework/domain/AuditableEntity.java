package cn.heshang.common.framework.domain;

import java.time.LocalDateTime;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/1/17 09:20
 * version: 1.0
 */
public abstract class AuditableEntity {
    protected LocalDateTime createdAt;
    protected Long createdBy;
    protected LocalDateTime lastUpdatedAt;
    protected Long lastUpdatedBy;

    public AuditableEntity(LocalDateTime createdAt, Long createdBy) {
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }


    // lastUPdatedAt 和 lastUpdatedBy 的 setter 以及所有属性的 getter ...

}
