package ink.hs.diy缓存.listener;

/**
 * @Author idea
 * @Date created in 10:18 下午 2022/8/29
 */
@FunctionalInterface
public interface RemovalListener {

    void onRemoval(String key);

}
