package ink.hs.diy缓存.listener;

/**
 * @Author idea
 * @Date created in 10:28 上午 2022/8/30
 */
@FunctionalInterface
public interface RefreshListener {

    /**
     * 刷新缓存数据
     */
    void doRefresh();
}
