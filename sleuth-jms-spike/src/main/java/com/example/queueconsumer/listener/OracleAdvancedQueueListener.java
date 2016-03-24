package com.example.queueconsumer.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
public class OracleAdvancedQueueListener {

    @JmsListener(destination = "FAKE_QUEUE", containerFactory = "fakeQueueJmsContainerFactory")
    public void dequeueOracleAdvancedQueueMessage(Message message) throws JMSException {

    }

}
