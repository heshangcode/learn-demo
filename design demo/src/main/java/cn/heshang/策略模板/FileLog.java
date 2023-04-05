package cn.heshang.策略模板;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/2/21 07:54
 * version: 1.0
 */
public class FileLog extends LogStrategyTemplate {
    @Override
    protected void doLog(String msg) {
        System.out.println("fileLog: " + msg);
    }
}
