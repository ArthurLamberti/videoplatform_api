package com.arthurlamberti.videoplataform.infrastructure.configuration;

import com.arthurlamberti.videoplataform.infrastructure.configuration.properties.GoogleCloudProperties;
import com.arthurlamberti.videoplataform.infrastructure.configuration.properties.GoogleStorageProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"development", "production"})
public class GoogleCloudConfig {

    @Bean
    @ConfigurationProperties("google.cloud")
    public GoogleCloudProperties googleCloudProperties () {
        return new GoogleCloudProperties();
    }

    @Bean
    @ConfigurationProperties("google.cloud.storage.catalogo-video")
    public GoogleStorageProperties googleStorageProperties () {
        return new GoogleStorageProperties();
    }
}
