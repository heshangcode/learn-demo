package cn.heshang.application.orgmng.empservice;

/**
 * 配合应用服务的装配器
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/2/7 09:35
 * version: 1.0
 */

import cn.heshang.domain.orgmng.emp.Emp;
import cn.heshang.domain.orgmng.emp.EmpHandler;
import cn.heshang.domain.orgmng.emp.Gender;
import cn.heshang.domain.orgmng.emp.SkillLevel;
import cn.heshang.domain.orgmng.org.validator.OrgValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
// imports...

@Component
public class EmpAssembler {
    EmpHandler handler; // Emp的领域服务
    OrgValidator orgValidator;

    @Autowired
    public EmpAssembler(EmpHandler handler, OrgValidator orgValidator) {
        this.handler = handler;
        this.orgValidator = orgValidator;
    }

    // 由 DTO 生成领域对象
    Emp fromCreateRequest(CreateEmpRequest request, User user) {
        //校验参数
        validateCreateRequest(request);

        // 生成员工号
        String empNum = handler.generateNum();

        Emp result = new Emp(request.getTenantId(), user.getId());
        result.setNum(empNum)
                .setIdNum(request.getIdNum())
                .setDob(request.getDob())
                .setOrgId(request.getOrgId())
                .setGender(Gender.ofCode(request.getGenderCode()));

        request.getSkills().forEach(s -> result.addSkill(
                s.getSkillTypeId()
                , SkillLevel.ofCode(s.getLevelCode())
                , s.getDuration()
                , user.getId()));

        request.getExperiences().forEach(e -> result.addExperience(
                e.getStartDate()
                , e.getEndDate()
                , e.getCompany()
                , user.getId()));

        return result;
    }

    void validateCreateRequest(CreateEmpRequest request) {
        //业务规则：组织应该有效
        orgValidator.orgShouldValid(
                request.getTenantId(), request.getOrgId());
    }

    // 将领域对象转换成 DTO
    EmpResponse toResponse(Emp emp) {
        // ...
    }
}
