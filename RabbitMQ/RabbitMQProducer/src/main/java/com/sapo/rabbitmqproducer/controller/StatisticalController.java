package com.sapo.rabbitmqproducer.controller;


import com.sapo.rabbitmqproducer.model.entity.Statistical;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sapo.rabbitmqproducer.rabbitmq.MessagingConfig;
import com.sapo.rabbitmqproducer.service.*;
import com.sapo.rabbitmqproducer.repository.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class StatisticalController {


    StatisticalService statisticalService;
    RabbitTemplate rabbitTemplate;

    @GetMapping("/rabbit/statistical")
    public ResponseEntity push(@RequestParam Integer storageId) {
        try {
            Statistical statistical = statisticalService.getByStorageId(storageId);

            rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, statistical);
            return ResponseEntity.ok(statistical);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/rabbit/statisticals")
    public ResponseEntity pushAll() {
        try {
            List<Statistical> list = statisticalService.getStatisticals();

            rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, list);

            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
