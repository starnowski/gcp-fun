package com.github.starnowski.service;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GCPStorage {

    @Value("${gcs.port}")
    private String gcsPort;


    public void init(){
        Storage storage = StorageOptions.newBuilder()
                .setHost("http://localhost:" + gcsPort)
                .build().getService();
        Bucket bucket = storage.create(BucketInfo.of("starnowski-bucket"));
    }

    public void saveStringAsFile(String name, String content) {
        //TODO


    }
}
