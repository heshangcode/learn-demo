package ink.heshang.validation分组;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2022/11/26 21:56
 * version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {

    @NotBlank
    private String name;

    /**
     * 年龄
     */
    @Max(value = 10, message = "年龄有误！", groups = {Group1.class})
    @Max(value = 100, message = "年龄有误！", groups = {Group2.class})
    private int age;

    /**
     * 保存的时候校验分组
     */
    public interface Save {
    }

    /**
     * 更新的时候校验分组
     */
    public interface Update {
    }

}
