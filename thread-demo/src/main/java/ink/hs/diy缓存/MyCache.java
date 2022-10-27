package ink.hs.diy缓存;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2022/10/27 09:27
 * version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyCache {
    private String key;
    private Object value;
    private long insertTime;
    private long ttl;
}
