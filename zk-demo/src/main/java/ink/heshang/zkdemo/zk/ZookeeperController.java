package ink.heshang.zkdemo.zk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2022/1/2 18:14
 * version: 1.0
 */
@RestController
@Slf4j
public class ZookeeperController {
    @Autowired
    private ZkApi zkApi;

    @GetMapping("/createnode")
    public boolean createNode(String path, String data) {
        log.debug("ZookeeperController create node {},{}", path, data);
        return zkApi.createNode(path, data);
    }
}
