package ink.hs.diy缓存;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2022/10/27 09:26
 * version: 1.0
 */
@Component("string")
public class StringCache implements ICache {

    private ConcurrentHashMap<String, MyCache> cache = new ConcurrentHashMap<>();


    @Override
    public boolean put(String key, Object value, long ttl) {
        MyCache myCache = new MyCache(key, value, System.currentTimeMillis(), ttl);
        cache.put(key, myCache);
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
