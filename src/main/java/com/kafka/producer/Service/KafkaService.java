package com.kafka.producer.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * KafkaService is responsible for producing messages to Kafka.
 *
 * This class uses KafkaTemplate to send messages.
 */
@Service
public class KafkaService {

    /**
     * KafkaTemplate is Spring Kafka’s abstraction
     * over KafkaProducer.
     *
     * Key type: String
     * Value type: Object
     */
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * Sends message to Kafka topic asynchronously.
     *
     * @param message message payload
     */
    public void sendMessage(String message) {

        // Send message to Kafka topic
        CompletableFuture<SendResult<String, Object>> future =
                kafkaTemplate.send("Test_topic", message);

        /**
         * Callback to handle success or failure
         * This executes asynchronously
         */
        future.whenComplete((result, ex) -> {

            if (ex == null) {
                // Message successfully sent
                System.out.println(
                        "Sent message=[" + message +
                                "] with offset=[" +
                                result.getRecordMetadata().offset() + "]"
                );
            } else {
                // Error occurred while sending message
                System.out.println(
                        "Unable to send message=[" +
                                message + "] due to : " + ex.getMessage()
                );
            }
        });
    }
}
