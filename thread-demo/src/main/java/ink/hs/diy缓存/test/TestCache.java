package ink.hs.diy缓存.test;

import ink.hs.diy缓存.ICache;
import ink.hs.diy缓存.StringCache;
import org.junit.jupiter.api.Test;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2022/11/2 09:18
 * version: 1.0
 */
public class TestCache {

    private ICache stringCache = new StringCache();


    @Test
    public void testAll() {
        stringCache.put("key1", "12", 10);
        stringCache.get("key1");
    }
}
