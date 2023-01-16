package cn.heshang.domain.orgmng;

import cn.heshang.domain.orgmng.Org;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/1/6 08:57
 * version: 1.0
 */
public interface OrgRepository {
    Optional findByIdAndStatus(long tenantId, Long id, OrgStatus status);

    int countBySuperiorAndName(long tenantId, Long superiorId, String name);

    boolean existsBySuperiorAndName(Long tenant, Long superior, String name);

    Org save(Org org);

}
