package com.nadiamm.statistics.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FileServiceTest {
    
    @Test
    public void shouldReadFile() throws IOException {
        String result = FileService.readFile("test.json");
        String expectedContent ="{\"test\":  \"test\"}";
        assertEquals(expectedContent, result);
    }
    @Test
    public void shouldFailToReadFile() throws IOException {
        String result = FileService.readFile("not-found-file.json");
        assertThrows(IOException.class, ()->FileService.readFile("not-found-file.json"));
    }
}