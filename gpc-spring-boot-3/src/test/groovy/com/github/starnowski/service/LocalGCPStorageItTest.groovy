package com.github.starnowski.service

import com.google.cloud.storage.Blob
import com.google.cloud.storage.BlobId
import com.google.cloud.storage.BlobInfo
import com.google.cloud.storage.Storage
import com.google.cloud.storage.contrib.nio.testing.LocalStorageHelper
import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Path

class LocalGCPStorageItTest extends Specification {

    /**
     * https://dev.to/diogomoreira/mocking-google-cloud-storage-blob-objects-517n
     * @return
     */
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
