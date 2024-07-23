package com.arthurlamberti.videoplataform.infrastructure.configuration;

import com.arthurlamberti.videoplataform.infrastructure.configuration.annotations.VideoCreatedQueue;
import com.arthurlamberti.videoplataform.infrastructure.configuration.properties.amqp.QueueProperties;
import com.arthurlamberti.videoplataform.infrastructure.services.EventService;
import com.arthurlamberti.videoplataform.infrastructure.services.impl.EventServiceImpl;
import com.arthurlamberti.videoplataform.infrastructure.services.local.InMemoryEventService;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class EventConfig {

    @Bean
    @VideoCreatedQueue
    @Profile({"development"})
    EventService localVideoCreatedEventService() {
        return new InMemoryEventService();
    }

    @VideoCreatedQueue
    @Bean
    @ConditionalOnMissingBean
    EventService videoCreatedEventService(
            @VideoCreatedQueue final QueueProperties props,
            final RabbitOperations ops
    ) {
        return new EventServiceImpl(props.getExchange(), props.getRoutingKey(), ops);
    }
}
