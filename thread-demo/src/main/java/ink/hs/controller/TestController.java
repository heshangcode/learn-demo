package ink.hs.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author 微信公众号《和尚的破功之路》
 * @date 2022/11/21 09:04
 * version: 1.0
 */
@RestController
public class TestController {
    @GetMapping("/test1/{id}")
    public String test1(@PathVariable("id") String id) {
        return id;
    }

    @PostMapping("/test2")
    public String test2(@RequestBody Test test) {
        return test.toString();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static
    class Test {
        String aa;
    }
}
