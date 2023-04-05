package cn.heshang.策略模板;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/2/21 07:56
 * version: 1.0
 */
public class LogClient {
    public static void main(String[] args) {
        LogContext logContext = new LogContext();
        logContext.log("Hello");
        logContext.log("Hello,12345");
    }
}
