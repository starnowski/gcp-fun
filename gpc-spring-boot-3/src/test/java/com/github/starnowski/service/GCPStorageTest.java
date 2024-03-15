package com.github.starnowski.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration
class GCPStorageTest {

    @Autowired
    private GCPStorage gcpStorage;

    @Test
    public void shouldUploadTextFileToGCPStorage() throws URISyntaxException, IOException {
        // GIVEN
        // Read all bytes from the file
        byte[] bytes = Files.readAllBytes(Path.of(this.getClass().getResource("test-text.txt").toURI()));
        String content = new String(bytes, StandardCharsets.UTF_8);

        // WHEN
        gcpStorage.saveStringAsFile("text-file.txt", content);



    }
}