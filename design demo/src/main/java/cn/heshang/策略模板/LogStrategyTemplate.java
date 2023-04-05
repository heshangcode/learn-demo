package cn.heshang.策略模板;

import sun.util.resources.LocaleData;

import java.time.LocalDateTime;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/2/21 07:51
 * version: 1.0
 */
public abstract class LogStrategyTemplate implements LogStrategy {
    @Override
    public void log(String msg) {
        LocalDateTime now = LocalDateTime.now();
        msg += now.toString();
        doLog(msg);
    }

    protected abstract void doLog(String msg);
}
