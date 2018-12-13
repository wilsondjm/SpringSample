package me.vincent.spring.tutorial.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.standard.TomcatRequestUpgradeStrategy;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

/**
 * WebSocket 配置
 *
 * @author wshuang
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /* Defines the client connect path */
        registry.addEndpoint("/v1/websocket").setAllowedOrigins("*").withSockJS();
        /* Enable java client connnection */
        registry.addEndpoint("/v1/websocket")
                .setHandshakeHandler(new DefaultHandshakeHandler(new TomcatRequestUpgradeStrategy()))
                .setAllowedOrigins("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /* Defines the client subscribed url prefix, server push url prefix */
        registry.enableSimpleBroker("/resp");
        /* Defines the client send url prefix, server receives prefix */
        registry.setApplicationDestinationPrefixes("/topic");
    }
}
