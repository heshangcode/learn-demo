package ink.hs;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2022/8/5 08:23
 * version: 1.0
 */
public class DaemonThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("jvm exit success!! ")));

        Thread testThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    System.out.println("thread still running ....");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        testThread.setDaemon(true);
        testThread.start();
        Thread.sleep(3000);
    }
}
