package com.reactiveinbox.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicsConfig {

    public static final String EVENTS_INBOX_TOPIC = "events-inbox";

    @Bean
    public NewTopic eventsInbox() {
        return new NewTopic(EVENTS_INBOX_TOPIC,1 ,(short) 1);
    }

}
