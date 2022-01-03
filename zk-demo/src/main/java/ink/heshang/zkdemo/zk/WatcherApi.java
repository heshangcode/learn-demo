package ink.heshang.zkdemo.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * WatcherApi 类实现 Watcher 接口，实现对 zookeeper 事件的监听。
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2022/1/2 18:12
 * version: 1.0
 */
@Slf4j
public class WatcherApi implements Watcher {


    @Override
    public void process(WatchedEvent event) {
        log.info("【Watcher监听事件】={}", event.getState());
        log.info("【监听路径为】={}", event.getPath());
        log.info("【监听的类型为】={}", event.getType()); //  三种监听类型： 创建，删除，更新
    }
}
