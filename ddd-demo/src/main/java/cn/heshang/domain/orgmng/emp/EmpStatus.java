package cn.heshang.domain.orgmng.emp;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/1/16 08:42
 * version: 1.0
 */
public enum EmpStatus {
    REGULAR("REG"), // 正式
    PROBATION("PRO"), // 试用期
    TERMINATED("TER"); // 终止
    private final String code;

    EmpStatus(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }

    public EmpStatus becomeRegular() {
        if (this != PROBATION) {
            throw new BusinessException("试用期员工才能转正！");
        }
        return REGULAR;
    }

    public EmpStatus terminate() {
        if (this == TERMINATED) {
            throw new BusinessException("已经终止的员工不能再次终止！");
        }
        return TERMINATED;
    }
}
