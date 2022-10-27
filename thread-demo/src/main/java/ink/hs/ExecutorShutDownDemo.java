package ink.hs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2022/8/5 09:17
 * version: 1.0
 */
public class ExecutorShutDownDemo {
    public static void testShutDown() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("[testShutDown] begin");
                    while (true) {
                    }
                }finally {
                    System.out.println("[testShutDown] end");
                }
            }
        });
        Thread.sleep(2000);
        executorService.shutdown();
        System.out.println("[testShutDown] shutdown");
    }

    public static void testShutDownNow() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("[testShutDownNow] begin");
                    while (true) {
                    }
                }finally {
                    System.out.println("[testShutDownNow] end");
                }
            }
        });
        Thread.sleep(2000);
        executorService.shutdownNow();
        System.out.println("[testShutDownNow] shutdownNow");
    }

    public static void main(String[] args) throws InterruptedException {
        testShutDown();
        testShutDownNow();
    }
}
