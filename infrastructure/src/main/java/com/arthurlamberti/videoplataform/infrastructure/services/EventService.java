package com.arthurlamberti.videoplataform.infrastructure.services;

import com.arthurlamberti.videoplataform.domain.events.DomainEvent;

public interface EventService {
    void send(Object event);
}
