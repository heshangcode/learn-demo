package ink.hs;

import java.util.concurrent.*;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2022/8/15 09:18
 * version: 1.0
 */
public class SubmitDemo {

    //如果 future
    //那岂不是变成了 future
    //阻塞，    的串行化流程

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 10L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        //Future<Integer> futureA = threadPoolExecutor.submit(() -> {
        //    Thread.sleep(10000);
        //    System.out.println("11111");
        //    return 1;
        //});
        //
        //Future<Integer> futureB = threadPoolExecutor.submit(() -> {
        //    //Thread.sleep(10000);
        //    System.out.println("2222");
        //    return 2;
        //});

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1111");
            return 1;
        });


        future1.thenAccept(System.out::println);

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("2222");
            return 2;
        });
        future2.thenAccept(System.out::println);


        //try {
        //    System.out.println(futureA.get(1, TimeUnit.SECONDS));
        //    System.out.println(futureB.get(1, TimeUnit.SECONDS));
        //} catch (InterruptedException | ExecutionException | TimeoutException e) {
        //    e.printStackTrace();
        //} finally {
        //    threadPoolExecutor.shutdown();
        //}
        //}

    }
}