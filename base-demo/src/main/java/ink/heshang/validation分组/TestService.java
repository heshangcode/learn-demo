package ink.heshang.validation分组;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2022/11/26 22:28
 * version: 1.0
 */
@Service
@Validated
public class TestService {

    @Valid
    public String checkGroup1(@Validated(Group1.class) Test test) {
        return "";
    }

    @Valid
    public String checkGroup2(@Validated(Group2.class) Test test) {
        System.out.println("checkGroup2");
        return "";
    }
}
