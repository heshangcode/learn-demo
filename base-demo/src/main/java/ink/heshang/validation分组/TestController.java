package ink.heshang.validation分组;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author 微信公众号《和尚的破功之路》
 * @date 2022/11/26 21:55
 * version: 1.0
 */
@RestController
@Validated
public class TestController {

    @Autowired
    private Validator globalValidator;

    @PostMapping("/testgroup1")
    public String testgroup1(@RequestBody Test test) {
        checkGroup1(test);
        return "testgroup1";
    }

    @PostMapping("/testgroup2")
    public String testgroup2(@RequestBody Test test) {
        checkGroup2(test);
        return "testgroup2";

    }

    @Valid
    public String checkGroup1( Test test) {
        Set<ConstraintViolation<Test>> validate = globalValidator.validate(test, Group1.class);
        if (validate.isEmpty()) {

        } else {
            for (ConstraintViolation<Test> testConstraintViolation : validate) {
                System.out.println(testConstraintViolation);
            }
        }

        return "";
    }

    @Valid
    public String checkGroup2(Test test) {Set<ConstraintViolation<Test>> validate = globalValidator.validate(test, Group2.class);
        if (validate.isEmpty()) {

        } else {
            for (ConstraintViolation<Test> testConstraintViolation : validate) {
                System.out.println(testConstraintViolation);
            }
        }
        return "";
    }


}
