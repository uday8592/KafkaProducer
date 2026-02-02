package com.kafka.producer.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * TopicConfig is responsible for Kafka topic creation.
 *
 * When Spring Boot application starts:
 *  - Spring Kafka Admin checks for this bean
 *  - If the topic does not exist, Kafka creates it automatically
 *
 * This avoids manual topic creation via kafka-topics.sh
 */
@Configuration
public class TopicConfig {

    /**
     * Creates a Kafka topic with:
     *  - Name: Test_topic
     *  - Partitions: 3  (allows parallel processing)
     *  - Replication factor: 1 (single broker, no redundancy)
     */
    @Bean
    public NewTopic topic() {

        return TopicBuilder
                .name("Test_event")   // Kafka topic name
                .partitions(3)        // Number of partitions
                .replicas(1)          // Replication factor
                .build();
    }
}
