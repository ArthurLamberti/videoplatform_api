package com.arthurlamberti.videoplataform.infrastructure.configuration;

import com.arthurlamberti.videoplataform.infrastructure.configuration.properties.google.GoogleStorageProperties;
import com.arthurlamberti.videoplataform.infrastructure.configuration.properties.storage.StorageProperties;
import com.arthurlamberti.videoplataform.infrastructure.services.StorageService;
import com.arthurlamberti.videoplataform.infrastructure.services.impl.GCStorageImpl;
import com.arthurlamberti.videoplataform.infrastructure.services.local.InMemoryStorageService;
import com.google.cloud.storage.Storage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class StorageConfig {

    @Bean
    @ConfigurationProperties(value = "storage.catalogo-videos")
    public StorageProperties storageProperties() {
        return new StorageProperties();
    }

    @Bean(name = "storageService")
    @Profile({"development", "production"})
    public StorageService gcStorageService(
            final GoogleStorageProperties props,
            final Storage storage
    ) {
        return new GCStorageImpl(props.getBucket(), storage);
    }

    @Bean(name = "storageService")
    @ConditionalOnMissingBean
    public StorageService inMemoryStorageService() {
        return new InMemoryStorageService();
    }
}
