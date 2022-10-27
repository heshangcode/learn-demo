package ink.hs.diy缓存;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2022/10/27 09:20
 * version: 1.0
 */
public interface ICache {
    boolean put(String key, Object value, long ttl);

    Object get(String key);

    boolean clearAll();

    boolean remove(String key);

    boolean containKey(String key);
}
