package com.github.starnowski;

import org.testcontainers.containers.GenericContainer;

public class GoogleCloudStorageContainer extends GenericContainer<GoogleCloudStorageContainer> {

    public GoogleCloudStorageContainer() {
        this.withExposedPorts(4443);
    }
}
