package cn.heshang.adapter.driven.resetful.orgmng;

import cn.heshang.application.orgmng.orgservice.OrgResponse;
import cn.heshang.application.orgmng.orgservice.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/1/6 08:57
 * version: 1.0
 */
@RestController
public class OrgController {
    private final OrgService orgService;

    @Autowired
    public OrgController(OrgService orgService) {
        this.orgService = orgService;
    }

    @PostMapping("/api/organizations")
    public OrgResponse addOrg(@RequestBody OrgResponse request) {
        //从请求里解析出 userId ...
        String userId = "";
        return orgService.addOrg(request, userId);
    }
}