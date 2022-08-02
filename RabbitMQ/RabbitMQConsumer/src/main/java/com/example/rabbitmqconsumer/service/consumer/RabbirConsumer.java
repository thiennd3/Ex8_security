package com.example.rabbitmqconsumer.service.consumer;

import com.example.rabbitmqconsumer.model.entity.Statistical;
import com.example.rabbitmqconsumer.rabbitmq.MessagingConfig;

import com.example.rabbitmqconsumer.service.StatisticalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component

public class RabbirConsumer {
    Logger logger = LoggerFactory.getLogger(RabbirConsumer.class);
    StatisticalService statisticalService;

    public RabbirConsumer(StatisticalService statisticalService) {
        this.statisticalService = statisticalService;
    }

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(Message message) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            List<Statistical> statisticals = Arrays.asList(objectMapper.readValue(message.getBody(), Statistical[].class));
            statisticalService.saveAll(statisticals);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
}
