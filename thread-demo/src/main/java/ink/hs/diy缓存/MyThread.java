package ink.hs.diy缓存;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2022/11/2 09:27
 * version: 1.0
 */
@Data
@AllArgsConstructor
public class MyThread {
    private Thread thread;
    private boolean statusIng;
}
