package ink.hs.diy缓存;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 别人逻辑：单独写了一个类做一些数据的存储，监听器的查看
 * 在这个类里注册监听器，初始化数据和配置
 *
 * 代码使用：先 init 配置，再进行功能使用
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2022/10/27 09:26
 * version: 1.0
 */
@Component("string")
public class StringCache implements ICache {

    private ConcurrentHashMap<String, MyCache> cache = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, MyThread> threadMap = new ConcurrentHashMap<>();

    private final String ttlThreadName = "ttlThread";

    @Override
    public boolean put(String key, Object value, long ttl) {
        MyCache myCache = new MyCache(key, value, System.currentTimeMillis(), ttl);
        cache.put(key, myCache);
        if (threadMap.contains(ttlThreadName)) {
            MyThread myThread = threadMap.get(ttlThreadName);
            if (!myThread.isStatusIng()) {
                myThread.getThread().start();
                myThread.setStatusIng(true);
            }
        } else {
            Thread ttlThread = new Thread(() -> {
                for (Map.Entry<String, MyCache> stringMyCacheEntry : cache.entrySet()) {
                    if (stringMyCacheEntry.getValue().getTtl() > 0) {
                        long localTime = System.currentTimeMillis();
                        if (localTime - stringMyCacheEntry.getValue().getInsertTime() > ttl) {
                            cache.remove(stringMyCacheEntry.getKey());
                        }
                    }
                }
                MyThread myThread = threadMap.get(ttlThreadName);
                myThread.setStatusIng(false);
            });
            threadMap.put(ttlThreadName, new MyThread(ttlThread, false));
        }

        return true;
    }

    @Override
    public Object get(String key) {
        if (containKey(key)) {
            return cache.get(key);
        }
        return null;
    }

    @Override
    public boolean clearAll() {
        cache.clear();
        return true;
    }

    @Override
    public boolean remove(String key) {
        cache.remove(key);
        return true;
    }

    @Override
    public boolean containKey(String key) {
        return cache.contains(key);
    }
}
