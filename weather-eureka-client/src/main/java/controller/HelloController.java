package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lipingxin
 * @Description:
 * @Date: 2018/3/22 10:31
 */

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String index() {
        return "Hello World!";
    }
}
