package com.arthurlamberti.videoplataform.infrastructure.configuration.properties.amqp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class QueueProperties implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(QueueProperties.class);

    private String exchange;
    private String routingKey;
    private String queue;

    @Override
    public void afterPropertiesSet() {
        log.debug(toString());
    }
}
