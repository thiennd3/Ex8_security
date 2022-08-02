package com.edu.sapo.ex9_kafka.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic newTopic3() {
        return TopicBuilder.name("skyblack3").build();
    }
    @Bean
    public NewTopic newTopic4() {
        return TopicBuilder.name("skyblack4").build();
    }
}
