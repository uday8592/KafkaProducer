package com.kafka.producer.Controller;

import com.kafka.producer.Service.KafkaService;
import com.kafka.producer.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * MessageController acts as an API layer.
 *
 * Client -> REST API -> Kafka Producer
 *
 * This controller does NOT talk to Kafka directly.
 * It delegates Kafka logic to KafkaService.
 */
@RestController
@RequestMapping("/kafka")
public class MessageController {

    /**
     * KafkaService is injected to send messages to Kafka
     */
    @Autowired
    private KafkaService kafkaService;

    /**
     * REST endpoint to publish message to Kafka
     *
     * Example:
     *  GET /kafka/publish/hello
     */
    @GetMapping("/publish/{message}")
    public ResponseEntity<String> publishMessage(@PathVariable String message) {
        for(int i=1;i<=1000;i++) {
            // Send message to Kafka topic

            kafkaService.sendMessage(message+i);
        }
        // Immediate HTTP response
        return ResponseEntity.ok("Message sent to Kafka topic");
    }

    @PostMapping("/event")
    public ResponseEntity<String> publishEvent(@RequestBody Customer customer){
        kafkaService.sendevent(customer);
        return ResponseEntity.ok("Event  sent to Kafka topic");
    }
}
