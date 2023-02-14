package cn.heshang.adapter.driving.persistence.orgmng;

import cn.heshang.domain.orgmng.emp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 仓库是对整个聚合，多个表进行操作
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/1/16 09:00
 * version: 1.0
 */
@Repository
public class EmpRepositoryJdbc implements EmpRepository {
    final JdbcTemplate jdbc; // SimpleJdbcInsert 是 Spring JDBC 提供的插入数据表的机制
    final SimpleJdbcInsert empInsert;
    final SimpleJdbcInsert skillInsert;
    final SimpleJdbcInsert insertWorkExperience;
    final SimpleJdbcInsert empPostInsert;

    @Autowired
    public EmpRepositoryJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.empInsert = new SimpleJdbcInsert(jdbc).withTableName("emp").usingGeneratedKeyColumns("id");
        // 初始化其他几个
        // SimpleJdbcInsrt ...

    }

    @Override
    public void save(Emp emp) {
        insertEmp(emp);  // 插入 emp 表

        //插入 skill 表
        emp.getSkills().forEach(s ->
                insertSkill(s, emp.getId()));
        //插入 work_experience 表
        emp.getExperiences().forEach(e ->
                insertWorkExperience(e, emp.getId()));
        //插入 emp_post表
        emp.getEmpPosts().forEach(p ->
                insertEmpPost(p, emp.getId()));

    }

    private void insertEmp(Emp emp) {
        Map<String, Object> parms = Map.of(
                "tenant_id", emp.getTenantId()
                , "org_id", emp.getOrgId()
                , "num", emp.getNum()
                , "id_num", emp.getIdNum()
                , "name", emp.getName()
                , "gender", emp.getGender().code()
                , "dob", emp.getDob()
                , "status", emp.getStatus().code()
                , "created_at", emp.getCreatedAt()
                , "created_by", emp.getCreatedBy()
        );

        Number createdId = empInsert.executeAndReturnKey(parms);
        //通过反射为私有 id 属性赋值
        forceSet(emp, "id", createdId.longValue());
    }

    private void insertWorkExperience(WorkExperience experience, Long empId) {
        // 类似 insertEmp...
    }

    private void insertSkill(Skill skill, Long empId) {
        // 类似 insertEmp...

    }

    private void insertEmpPost(EmpPost empPost, Long empId) {
        // 类似 insertEmp...
    }

    // 其他方法 ...
}
