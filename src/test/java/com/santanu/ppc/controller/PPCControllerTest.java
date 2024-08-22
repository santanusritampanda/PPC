package com.santanu.ppc.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.santanu.ppc.controller.PPCController;
import com.santanu.ppc.service.PPCService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class PPCControllerTest {

    @Mock
    private PPCService ppcService;

    @InjectMocks
    private PPCController ppcController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testFileWithInvalidData() throws Exception {
        String fileContent = "1, , Santanu, Sritam, Developer, ONBOARD, 01-01-2020, 01-01-2020, Onboard\n";
        MultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", fileContent.getBytes());

        doNothing().when(ppcService).processFiles(any(InputStream.class));

        String response = ppcController.processFiles(List.of(file));
        assertTrue(response.contains("File Uploaded Successfully: test.csv"));

        verify(ppcService, times(1)).processFiles(any(InputStream.class));
    }
    @Test
    void testUploadFiles_withSingleFile() throws Exception {
        String fileContent = "1, E001, Santanu, Sritam, Developer, ONBOARD, 01-01-2020, 01-01-2020, Onboard\n";
        MultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", fileContent.getBytes());

        doNothing().when(ppcService).processFiles(any(InputStream.class));

        String response = ppcController.processFiles(List.of(file));
        assertTrue(response.contains("File Uploaded Successfully: test.csv"));

        verify(ppcService, times(1)).processFiles(any(InputStream.class));
    }

    @Test
    void testUploadFiles_withMultipleFiles() throws Exception {
        String fileContent1 = "1, E001, Santanu, Sritam, Developer, ONBOARD, 01-01-2020, 01-01-2020, Onboard\n";
        String fileContent2 = "2, E002, Mikun, Panda, Designer, ONBOARD, 01-01-2020, 01-01-2020, Onboard\n";
        MultipartFile file1 = new MockMultipartFile("file", "test1.csv", "text/csv", fileContent1.getBytes());
        MultipartFile file2 = new MockMultipartFile("file", "test2.txt", "text/plain", fileContent2.getBytes());

        doNothing().when(ppcService).processFiles(any(InputStream.class));

        String response = ppcController.processFiles(List.of(file1, file2));
        assertTrue(response.contains("File Uploaded Successfully: test1.csv"));
        assertTrue(response.contains("File Uploaded Successfully: test2.txt"));

        verify(ppcService, times(2)).processFiles(any(InputStream.class));
    }

    @Test
    void testUploadFiles_withIOException() throws Exception {
        MultipartFile file = mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn("test.csv");
        when(file.getInputStream()).thenThrow(new IOException("Test IOException"));

        String response = ppcController.processFiles(List.of(file));
        assertTrue(response.contains("Failed to upload file: test.csv - Test IOException"));

        verify(ppcService, never()).processFiles(any(InputStream.class));
    }
}