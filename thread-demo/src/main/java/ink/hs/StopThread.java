package ink.hs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2022/8/5 09:09
 * version: 1.0
 */
public class StopThread {
    static class TestThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.print(i+" ");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class TestThreadWithSync implements Runnable {

        @Override
        public void run() {
            synchronized (this) {
                for (int i = 20; i < 30; i++) {
                    System.out.print(i+" ");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    static class TestThreadWithLock implements Runnable {

        ReentrantLock reentrantLock = new ReentrantLock();

        @Override
        public void run() {
            reentrantLock.lock();
            try {
                for (int i = 30; i < 40; i++) {
                    System.out.print(i+" ");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread testThread = new Thread(new TestThread());
        testThread.start();
        Thread testThreadWithSync = new Thread(new TestThreadWithSync());
        testThreadWithSync.start();
        Thread testThreadWithLock = new Thread(new TestThreadWithLock());
        testThreadWithLock.start();

        Thread.sleep(2000);
        testThread.stop();
        testThreadWithSync.stop();
        testThreadWithLock.stop();

    }
}
