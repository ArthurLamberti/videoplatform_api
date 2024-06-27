package com.arthurlamberti.videoplataform.infrastructure.configuration.properties.google;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

@Getter
@Setter
public class GoogleStorageProperties implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(GoogleCloudProperties.class);
    private String bucket;
    private int connectTimeout;
    private int readTimeout;
    private int retryDelay;
    private int retryMaxDelay;
    private int retryMaxAttempts;
    private double retryMultiplier;

    @Override
    public void afterPropertiesSet() {
        log.debug(toString());
    }

    @Override
    public String toString() {
        return "GoogleStorageProperties{" +
                "bucket='" + bucket + '\'' +
                ", connectTimeout=" + connectTimeout +
                ", readTimeout=" + readTimeout +
                ", retryDelay=" + retryDelay +
                ", retryMaxDelay=" + retryMaxDelay +
                ", retryMaxAttempts=" + retryMaxAttempts +
                ", retryMultiplier=" + retryMultiplier +
                '}';
    }
}

