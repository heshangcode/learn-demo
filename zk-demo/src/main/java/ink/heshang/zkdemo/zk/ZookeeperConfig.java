package ink.heshang.zkdemo.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2022/1/2 18:04
 * version: 1.0
 */
@Configuration
@Slf4j
public class ZookeeperConfig {

    @Value("${zookeeper.address}")
    private String connectString;
    @Value("${zookeeper.timeout}")
    private int timeout;

    @Bean(name = "zkClient")
    public ZooKeeper zkClient() {
        ZooKeeper zooKeeper = null;
        try {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            zooKeeper = new ZooKeeper(connectString, timeout, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                        countDownLatch.countDown();
                    }
                }
            });
            countDownLatch.await();
            log.info("【初始化ZooKeeper连接状态....】={}", zooKeeper.getState());
        } catch (Exception e) {
            log.error("初始化ZooKeeper连接异常....】", e);
        }

        return zooKeeper;
    }
}
