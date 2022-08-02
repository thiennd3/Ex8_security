package com.edu.sapo.ex9_kafka.kafka;

import com.edu.sapo.ex9_kafka.model.entity.Statistical;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.internals.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KafkaProducer {
    private static Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
    private KafkaTemplate<String, Statistical> kafkaTemplateJson;
    private  KafkaTemplate<String, List<Statistical>> kafkaTemplateForList;

    public void sendStatistical(Statistical statistical) {
        Message<Statistical> message = MessageBuilder.withPayload(statistical).setHeader(KafkaHeaders.TOPIC, "skyblack3").build();
        kafkaTemplateJson.send("skyblack3", statistical);


    }
    public void sendStatisticals(List<Statistical> statisticals) {
        Message<List<Statistical>> message = MessageBuilder.withPayload(statisticals).setHeader(KafkaHeaders.TOPIC, "skyblack4").build();
        kafkaTemplateForList.send( message);


    }

}
