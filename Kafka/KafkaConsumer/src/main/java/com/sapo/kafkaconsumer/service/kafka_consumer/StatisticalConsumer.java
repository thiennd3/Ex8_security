package com.sapo.kafkaconsumer.service.kafka_consumer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapo.kafkaconsumer.model.dto.StatisticalDTO;
import com.sapo.kafkaconsumer.model.entity.Statistical;
import com.sapo.kafkaconsumer.service.StatisticalService;

import lombok.AllArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class StatisticalConsumer {

    StatisticalService statisticalService;

    @KafkaListener(topics = "skyblack5", groupId = "myGroup1")
    public void saveStatistical(Message message) {
        try {
//
//            JSONObject jsonObject = new JSONObject(message.getPayload().toString());
//            Statistical statistical = new Statistical();
//            statistical.setNumber(jsonObject.getInt("number"));
//            statistical.setStorageId(jsonObject.getInt("storageId"));
//            statistical.setCreateAt(new Timestamp(jsonObject.getLong("createAt")));

            ObjectMapper objectMapper = new ObjectMapper();
            Statistical statistical= objectMapper.readValue(message.getPayload().toString(),Statistical.class);


            statisticalService.save(statistical);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @KafkaListener(topics = "skyblack6", groupId = "myGroup1")
    public void saveStatisticals(Message message) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            List<Statistical> statisticals = Arrays.asList(objectMapper.readValue(message.getPayload().toString(), Statistical[].class));
            statisticalService.save(statisticals);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
