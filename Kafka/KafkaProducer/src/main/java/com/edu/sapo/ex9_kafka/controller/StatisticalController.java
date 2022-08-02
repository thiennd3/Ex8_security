package com.edu.sapo.ex9_kafka.controller;

import com.edu.sapo.ex9_kafka.kafka.KafkaProducer;
import com.edu.sapo.ex9_kafka.model.entity.Statistical;
import com.edu.sapo.ex9_kafka.service.StatisticalService;
import com.edu.sapo.ex9_kafka.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
public class StatisticalController {


    StatisticalService statisticalService;
    KafkaProducer kafkaProducer;

    @GetMapping("/statistical")
    public ResponseEntity push(@RequestParam Integer storageId) {
        try {
            Statistical statistical = statisticalService.getByStorageId(storageId);

            kafkaProducer.sendStatistical(statistical);

            return ResponseEntity.ok(statistical);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/statisticals")
    public ResponseEntity pushAll() {
        try {
            List<Statistical> list = statisticalService.getStatisticals();

            kafkaProducer.sendStatisticals(list);

            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
