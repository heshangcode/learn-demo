package cn.heshang.domain.orgmng.emp;

import java.time.LocalDate;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/2/15 06:19
 * version: 1.0
 */
public class Period {
    private LocalDate start;
    private LocalDate end;

    private Period(LocalDate start, LocalDate end) {
        //创建时校验基本规则
        if (start == null || end == null || start.isAfter(end)) {
            throw new IllegalArgumentException("开始和结束日期不能为空，且结束日期不能小于开始日期！");
        }
        this.start = start;
        this.end = end;
    }

    //用于构造对象
    public static Period of(LocalDate start, LocalDate end) {
        return new Period(start, end);
    }

    public boolean overlap(Period other) {
        if (other == null) {
            throw new IllegalArgumentException("入参不能为空！");
        }
        return other.start.isBefore(this.end) && other.end.isAfter(this.start);
    }

    // 合并两个时间段
    public Period merge(Period other) {
        LocalDate newStart = this.start.isBefore(other.start) ? this.start : other.start;
        LocalDate newEnd = this.end.isAfter(other.end) ? this.end : other.end;
        return new Period(newStart, newEnd);
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }
}
