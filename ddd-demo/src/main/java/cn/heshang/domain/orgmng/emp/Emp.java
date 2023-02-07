package cn.heshang.domain.orgmng.emp;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/1/16 08:42
 * version: 1.0
 */

import cn.heshang.common.framework.domain.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
public class Emp extends AuditableEntity {
    private Long id;          // 只读
    private Long tenantId;    // 只读
    private Long orgId;        // 读写
    private String num;       // 读写，员工编号
    private String idNum;     // 读写，身份证号
    private Gender Gender;    // 读写
    private LocalDate dob;    // 读写
    private EmpStatus status; // 读写


    // 这里就是非聚合根的单对象关联
    private List<Skill> skills;              // 读写
    private List<WorkExperience> experiences;// 读写
    private List<String> postCodes;          // 读写，岗位代码

    public Emp(Long tenantId, LocalDateTime createdAt, Long createdBy) {
        super(createdAt, createdBy);
        this.tenantId = tenantId;
    }

    // other getters and setters ...

    public EmpStatus getStatus() {
        return status;
    }

    public void becomeRegular() {
        status = EmpStatus.REGULAR;
    }

    public void terminate() {
        status = EmpStatus.TERMINATED;
    }

    // 对 skills、experiences 和 postCodes 的操作 ...

    void addSkill(Long skillTypeId, SkillLevel level, int duration, Long userId) {
        Skill newSkill = new Skill(tenantId, skillTypeId, LocalDateTime.now(), userId);
        newSkill.setLevel(level);
        newSkill.setDuration(duration);
        skills.add(newSkill);
    }

    public List<Skill> getSkills() {
        return Collections.unmodifiableList(skills);
    }

    // TODO: 2023/2/7 问题 为什么方法参数用包装类型，不担心传 null 的问题吗？
    public Optional<Skill> getSkill(Long skillTypeId) {
        return skills.stream().filter(s -> s.getSkillTypeId() == skillTypeId).findAny();
    }
}
