package ink.hs;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2022/8/11 08:31
 * version: 1.0
 */
public class PrintAbcDemo_1 {

    private int signal = 0;

    public synchronized void printA() throws InterruptedException {
        while (signal != 0) {
            wait();
        }

        System.out.println("a");
        signal = 1;
        notifyAll();
        //Thread.sleep(1000);
    }

    public synchronized void printB() throws InterruptedException {
        while (signal != 1) {
            wait();
        }

        System.out.println("b");
        signal = 2;
        notifyAll();
        //Thread.sleep(1000);
    }

    public synchronized void printC() throws InterruptedException {
        while (signal != 2) {
            wait();
        }

        System.out.println("c");
        signal = 0;
        notifyAll();
        //Thread.sleep(1000);
    }

    public static void main(String[] args) {
        PrintAbcDemo_1 printAbcDemo_1 = new PrintAbcDemo_1();

        Thread threadA = new Thread(() -> {
            try {
                printAbcDemo_1.printA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                printAbcDemo_1.printB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                printAbcDemo_1.printC();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();


    }
}
