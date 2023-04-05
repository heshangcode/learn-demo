package cn.heshang.策略模板;

import java.util.Optional;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/2/21 07:55
 * version: 1.0
 */
public class LogContext {

    public void log(String msg) {

        LogStrategy log = new DbLog();
        try {
            log.log(msg);
        } catch (Exception e) {
            log = new FileLog();
            log.log(msg);
        }
    }
}
