package com.nadiamm.statistics.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class FileService {
    public static String readFile(String filename) throws IOException {
        File resource = new ClassPathResource(
                filename).getFile();
        return new String(Files.readAllBytes(resource.toPath()));
    }
}
