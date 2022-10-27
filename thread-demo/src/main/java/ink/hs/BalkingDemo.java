package ink.hs;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2022/8/15 09:05
 * version: 1.0
 */
public class BalkingDemo {

    private volatile boolean isChange = false;

    public static void main(String[] args) throws InterruptedException {
        BalkingDemo balkingDemo = new BalkingDemo();
        for (int i = 0; i < 10; i++) {
            Thread saveThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    balkingDemo.saveFile();
                }
            });
            Thread changeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    balkingDemo.changeFile();
                }
            });
            saveThread.start();
            changeThread.start();
            Thread.sleep(1000);
        }

    }

    public void saveFile() {
        if (isChange) {
            //将缓冲区的代码写入到磁盘中
            System.out.println("保存磁盘");
            isChange = false;
        }
    }

    public void changeFile() {
        isChange = true;
        //修改缓冲区的数据
        System.out.println("修改缓冲数据");
    }
}
