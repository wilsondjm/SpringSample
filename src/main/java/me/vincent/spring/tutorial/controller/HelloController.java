package me.vincent.spring.tutorial.controller;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @description  Websocket 测试代码，Hello {name}!
 *
 * @author wshuang
 */

@Controller
public class HelloController {

    @MessageMapping("/hello")
    @SendTo("/resp/hello")
    public String greeting(String name){
        return String.format("Hello %s！", name);
    }

}
