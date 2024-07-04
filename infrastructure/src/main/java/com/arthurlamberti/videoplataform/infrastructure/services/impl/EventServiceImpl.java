package com.arthurlamberti.videoplataform.infrastructure.services.impl;

import com.arthurlamberti.videoplataform.infrastructure.configuration.json.Json;
import com.arthurlamberti.videoplataform.infrastructure.services.EventService;
import org.springframework.amqp.rabbit.core.RabbitOperations;

import java.util.Objects;

public class EventServiceImpl implements EventService {

    private final String exchage;
    private final String routingKey;
    private final RabbitOperations ops;

    public EventServiceImpl(String exchage, String routingKey, RabbitOperations ops) {
        this.exchage = Objects.requireNonNull(exchage);
        this.routingKey = Objects.requireNonNull(routingKey);
        this.ops = Objects.requireNonNull(ops);
    }

    @Override
    public void send(Object event) {
        this.ops.convertAndSend(this.exchage, this.routingKey, Json.writeValueAsString(event));
    }
}
