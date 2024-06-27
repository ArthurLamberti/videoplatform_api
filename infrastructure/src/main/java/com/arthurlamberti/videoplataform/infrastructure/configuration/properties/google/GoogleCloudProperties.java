package com.arthurlamberti.videoplataform.infrastructure.configuration.properties.google;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

@Getter
@Setter
public class GoogleCloudProperties implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(GoogleCloudProperties.class);

    private String credentials;
    private String projectId;

    @Override
    public void afterPropertiesSet() {
        log.debug(toString());
    }

    @Override
    public String toString() {
        return "GoogleCloudProperties{" +
                ", projectId='" + projectId + '\'' +
                '}';
    }
}
