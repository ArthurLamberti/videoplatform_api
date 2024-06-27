package com.arthurlamberti.videoplataform.infrastructure.configuration.properties.storage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;


@NoArgsConstructor
@Getter
@Setter
public class StorageProperties implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(StorageProperties.class);
    private String locationPattern;
    private String filenamePattern;

    @Override
    public void afterPropertiesSet() {
        log.debug(toString());
    }

    @Override
    public String toString() {
        return "StorageProperties{" +
                "locationPattern='" + locationPattern + '\'' +
                ", filenamePattern='" + filenamePattern + '\'' +
                '}';
    }
}
