package com.github.starnowski.service.configuration;

import com.github.starnowski.GoogleCloudStorageContainer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GCPStorageContainerConfiguration {

    @Autowired
    private ConfigurableApplicationContext configurableApplicationContext;

    @Bean
    public GoogleCloudStorageContainer getGoogleCloudStorageContainer()
    {
        GoogleCloudStorageContainer container = new GoogleCloudStorageContainer();
        container.start();
        return container;
    }
}
