package ru.knastnt.springjmsibmmq.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Sender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Async("threadPoolTaskExecutor")
    @JmsListener(destination = "${client.publ.jms.queue}", containerFactory = "jmsContainer")
    public void sendMessage(String message) {

        jmsTemplate.send("DEV.QUEUE.1", session -> {
            return session.createTextMessage(message);
        });
    }
}
