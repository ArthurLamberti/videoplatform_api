package com.arthurlamberti.videoplataform.infrastructure.configuration;

import com.arthurlamberti.videoplataform.infrastructure.configuration.annotations.VideoCreatedQueue;
import com.arthurlamberti.videoplataform.infrastructure.configuration.properties.amqp.QueueProperties;
import com.arthurlamberti.videoplataform.infrastructure.services.EventService;
import com.arthurlamberti.videoplataform.infrastructure.services.impl.EventServiceImpl;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConfig {

    @VideoCreatedQueue
    @Bean
    EventService videoCreatedEventService(
            @VideoCreatedQueue final QueueProperties props,
            final RabbitOperations ops
    ) {
        return new EventServiceImpl(props.getExchange(), props.getRoutingKey(), ops);
    }

}
