package cn.heshang.策略模板;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/2/21 07:53
 * version: 1.0
 */
public class DbLog extends LogStrategyTemplate{
    @Override
    protected void doLog(String msg) {
        if (msg.trim().length() > 5) {
            int i = 5 / 0;
        }
        System.out.println("dbLog" + msg);
    }
}
