package com.academy.config;


/*
@Configuration
public class MessagingConfig {

    private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";

    private static final String TIMETABLE_QUEUE = "TimetableQueue";

    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
        connectionFactory.setTrustedPackages(Collections.singletonList("com.academy"));
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(TIMETABLE_QUEUE);
        return template;
    }
}

 */
