package me.vincent.spring.tutorial.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WebSocketPushScheduler {

    @Autowired
    private SimpMessagingTemplate simpleMessagingTemplate;

    @Scheduled(fixedRate = 5000, initialDelay = 10 * 1000)
    public void pushMessageOverWebSocket(){
        System.out.println("****************** scheduled ****************");
        simpleMessagingTemplate.convertAndSend("/resp/hello", "Connected");
    }
}
