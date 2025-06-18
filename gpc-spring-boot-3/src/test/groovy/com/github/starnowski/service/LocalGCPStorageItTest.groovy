package com.github.starnowski.service

import com.github.starnowski.GoogleCloudStorageContainer
import com.google.cloud.NoCredentials
import com.google.cloud.storage.Blob
import com.google.cloud.storage.BlobId
import com.google.cloud.storage.BlobInfo
import com.google.cloud.storage.BucketInfo
import com.google.cloud.storage.Storage
import com.google.cloud.storage.StorageOptions
import com.google.cloud.storage.contrib.nio.testing.LocalStorageHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Path

class LocalGCPStorageItTest extends Specification {


    def "should upload text file to gcp storage" () {
        given:
        Storage storage = LocalStorageHelper.getOptions().getService()
        BlobInfo blobInfo = BlobInfo.newBuilder(
                BlobId.of("starnowski-bucket", "my-mock-object"))
                .build()
        // Read all bytes from the file
            byte[] bytes = Files.readAllBytes(Path.of(this.getClass().getResource("test-text.txt").toURI()))

        when:
            storage.create(blobInfo, bytes)

        then:
            Blob blob = storage.get(blobInfo.getBlobId())
            blob.getBlobId().name == "my-mock-object"
            blob.getContent() == bytes
    }
}
