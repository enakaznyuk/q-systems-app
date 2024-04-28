package ru.systems.demo.mq;

import com.google.gson.Gson;
import jakarta.jms.JMSException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import jakarta.jms.Message;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.systems.demo.message.MessageJson;
import ru.systems.demo.rest.PhoneComponent;
import ru.systems.demo.rest.WorkerComponent;

@Slf4j
@Component
public class Receiver {

    @Autowired
    private PhoneComponent phoneComponent;

    @Autowired
    private WorkerComponent workerComponent;

    @Async("threadPoolTaskExecutor")
    @JmsListener(destination = "${client.publ.jms.queue}", containerFactory = "jmsContainer")
    public void receiveMessage(final Message message) throws JMSException, InterruptedException {

        MessageJson messageJson = new Gson().fromJson(message.getBody(String.class), MessageJson.class);

        switch (messageJson.getObjectEntity()) {
            case PHONE -> phoneComponent.getMessage(messageJson);
            case WORKER -> workerComponent.getMessage(messageJson);
        }
    }
}
