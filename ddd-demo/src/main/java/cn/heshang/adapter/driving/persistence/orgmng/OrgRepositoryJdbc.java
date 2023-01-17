package cn.heshang.adapter.driving.persistence.orgmng;

import cn.heshang.domain.orgmng.org.Org;
import cn.heshang.domain.orgmng.org.OrgRepository;
import cn.heshang.domain.orgmng.org.OrgStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/1/16 09:00
 * version: 1.0
 */

@Repository
public class OrgRepositoryJdbc implements OrgRepository {
    JdbcTemplate jdbc;
    SimpleJdbcInsert insertOrg;

    @Autowired
    public OrgRepositoryJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Optional<Org> findByIdAndStatus(Long tenantId, Long id, OrgStatus status) {
        //...
    }

    @Override
    public int countBySuperiorAndName(Long tenantId, Long superiorId, String name) {
        //...
    }

    @Override
    public boolean existsBySuperiorAndName(Long tenant, Long superior, String name) {
        //...
    }

    @Override
    public Org save(Org org) {
        //...
    }
}