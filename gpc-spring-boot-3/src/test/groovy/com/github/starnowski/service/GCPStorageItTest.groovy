package com.github.starnowski.service

import com.github.starnowski.GoogleCloudStorageContainer
import com.google.cloud.NoCredentials
import com.google.cloud.storage.Bucket
import com.google.cloud.storage.BucketInfo
import com.google.cloud.storage.Storage
import com.google.cloud.storage.StorageOptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.spock.Testcontainers
import spock.lang.Specification

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = GCPStorageApplication)
@ContextConfiguration
class GCPStorageItTest extends Specification {

    @Value("\${gcs.port}")
    private int gcsPort
//    @Autowired
//    private GCPStorage gcpStorage
    @Autowired
    private GoogleCloudStorageContainer googleCloudStorageContainer

    def "should upload text file to gcp storage" () {
        given:
        Storage storage = StorageOptions.newBuilder()
                .setProjectId("testXXX")
                .setHost("http://" + googleCloudStorageContainer.getHost() + ":" + googleCloudStorageContainer.getMappedPort(gcsPort))
                .setCredentials(NoCredentials.getInstance())
                .build().getService()
        def bucket = storage.create(BucketInfo.of("starnowski-bucket"))
        // Read all bytes from the file
            byte[] bytes = Files.readAllBytes(Path.of(this.getClass().getResource("test-text.txt").toURI()))

        when:
            bucket.create("text-file.txt", bytes, "text/plain")

        then:
            def blob = bucket.get("text-file.txt")
            blob.getBlobId().name == "text-file.txt"
            blob.getContent() == bytes
    }
}
