package com.arthurlamberti.videoplataform.infrastructure.services.local;

import com.arthurlamberti.videoplataform.infrastructure.configuration.json.Json;
import com.arthurlamberti.videoplataform.infrastructure.services.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InMemoryEventService implements EventService {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryEventService.class);

    @Override
    public void send(Object event) {
        LOG.info("Event was observed: {}", Json.writeValueAsString(event));
    }
}
