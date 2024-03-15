package com.github.starnowski;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class GCPStorageContainerConfiguration {

    @Autowired
    private ConfigurableApplicationContext configurableApplicationContext;

    @Bean
    public GoogleCloudStorageContainer getGoogleCloudStorageContainer()
    {
        GoogleCloudStorageContainer container = new GoogleCloudStorageContainer();
//        container.addEnv("MONGO_INITDB_ROOT_USERNAME", MONGO_ADMIN_NAME);
//        container.addEnv("MONGO_INITDB_ROOT_PASSWORD", MONGO_ADMIN_PASSWORD);
        container.start();
        return container;
    }

    @PostConstruct
    public void googleCloudStorageContainerInitializer() {
        GoogleCloudStorageContainer container = getGoogleCloudStorageContainer();
//        TestPropertyValues values = TestPropertyValues.of(
//                "spring.data.mongodb.host=" + container.getContainerIpAddress(),
//                "spring.data.mongodb.port=" + container.getPort(),
//                "spring.data.mongodb.username=" + MONGO_ADMIN_NAME,
//                "spring.data.mongodb.password=" + MONGO_ADMIN_PASSWORD,
//                "spring.data.mongodb.authentication-database=" + "admin"
//
//        );
//        values.applyTo(configurableApplicationContext);
    }
}
