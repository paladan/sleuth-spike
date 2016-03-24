package com.example.configuration;

import oracle.jms.AQjmsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.destination.DestinationResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class OracleAdvancedQueueConfiguration {
    @Autowired(required = false)
    private DestinationResolver destinationResolver;

    @Autowired(required = false)
    private JtaTransactionManager transactionManager;

    @Autowired
    private JmsProperties properties;

    @Bean(name = "fakeQueueJmsContainerFactory")
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);

        factory.setPubSubDomain(this.properties.isPubSubDomain());
        if (this.transactionManager != null) {
            factory.setTransactionManager(this.transactionManager);
        } else {
            factory.setSessionTransacted(true);
        }
        if (this.destinationResolver != null) {
            factory.setDestinationResolver(this.destinationResolver);
        }
        JmsProperties.Listener listener = this.properties.getListener();
        factory.setAutoStartup(listener.isAutoStartup());
        if (listener.getAcknowledgeMode() != null) {
            factory.setSessionAcknowledgeMode(listener.getAcknowledgeMode().getMode());
        }
        String concurrency = listener.formatConcurrency();
        if (concurrency != null) {
            factory.setConcurrency(concurrency);
        }

        return factory;
    }

    @Bean
    public ConnectionFactory connectionFactory(
            @Qualifier("oracleAdvancedQueueDataSource") DataSource oracleAdvancedQueueDataSource) throws JMSException {
        return AQjmsFactory.getConnectionFactory(oracleAdvancedQueueDataSource);
    }
}
