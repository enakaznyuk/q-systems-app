package ru.knastnt.springjmsibmmq.config;

import com.ibm.mq.jakarta.jms.MQConnectionFactory;
import com.ibm.mq.spring.boot.MQConfigurationProperties;
import com.ibm.mq.spring.boot.MQConnectionFactoryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Slf4j
@Configuration
public class IbmMqConfiguration {

    @Autowired
    private AsyncConfiguration asyncConfiguration;

    @Bean
    @ConfigurationProperties(prefix = "client.publ.jms")
    public MQConfigurationProperties configProperties() {

        return new MQConfigurationProperties();
    }

    @Bean
    public MQConnectionFactory connectionFactory(MQConfigurationProperties configProperties) {

        return new MQConnectionFactoryFactory(configProperties, null, null)
                .createConnectionFactory(MQConnectionFactory.class);
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsContainer(MQConnectionFactory connectionFactory) {

        DefaultJmsListenerContainerFactory listenerContainer = new DefaultJmsListenerContainerFactory();
        listenerContainer.setPubSubDomain(true);
        listenerContainer.setConnectionFactory(connectionFactory);
        listenerContainer.setTaskExecutor(asyncConfiguration.getAsyncExecutor());
        listenerContainer.setErrorHandler(error -> log.error("Error!!! has occurred in jms listener", error));
        return listenerContainer;
    }

    @Bean
    public JmsTemplate jmsTemplate(MQConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
        return jmsTemplate;
    }
}
