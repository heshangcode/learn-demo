package ink.hs;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2022/8/5 08:30
 * version: 1.0
 */
public class ThreadExceptionCatchDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("this is test");
            int i = 10 / 0;
        });
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.err.println("thread is "+t.getName());
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
