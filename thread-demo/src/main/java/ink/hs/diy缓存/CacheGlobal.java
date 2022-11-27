package ink.hs.diy缓存;

import ink.hs.diy缓存.listener.AddListener;
import ink.hs.diy缓存.listener.ReadListener;
import ink.hs.diy缓存.listener.RemovalListener;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2022/11/3 08:47
 * version: 1.0
 */
@Data
public class CacheGlobal {
    public ConcurrentHashMap<String, MyCache> cacheConcurrentHashMap = new ConcurrentHashMap<>();

    private int checkTime;
    private boolean needClean;
    private int maxmumSize;

    private List<AddListener> addListeners = new ArrayList<>();
    private List<RemovalListener> removalListeners = new ArrayList<>();
    private List<ReadListener> readListeners = new ArrayList<>();

    public CacheGlobal() {

    }
    public CacheGlobal(int checkTime, boolean needClean) {
        this.checkTime = checkTime;
        this.needClean = needClean;
    }

    public CacheGlobal maxmumSize(int maxmumSize) {
        this.maxmumSize = maxmumSize;
        return this;
    }
    public static CacheGlobal DEFAULT_CACHE() {
        return new CacheGlobal(5, true);
    }

    public CacheGlobal addAddListener(AddListener addListener) {
        this.addListeners.add(addListener);
        return this;
    }

    public CacheGlobal addReadListener(ReadListener readListener) {
        this.readListeners.add(readListener);
        return this;
    }

    public CacheGlobal addRemovalListener(RemovalListener removalListener) {
        this.removalListeners.add(removalListener);
        return this;
    }



}
