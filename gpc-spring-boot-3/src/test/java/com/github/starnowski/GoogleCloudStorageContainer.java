package com.github.starnowski;

import org.testcontainers.containers.GenericContainer;

public class GoogleCloudStorageContainer extends GenericContainer<GoogleCloudStorageContainer> {

    public GoogleCloudStorageContainer() {
        super("fsouza/fake-gcs-server");
        this.withExposedPorts(4443)
                .withCreateContainerCmdModifier(createContainerCmd -> createContainerCmd.withEntrypoint(
                                "/bin/fake-gcs-server", "-scheme", "http"
                        )
                );
    }
}
