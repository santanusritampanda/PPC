package com.santanu.ppc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.santanu.ppc.service.PPCService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ppcapi/payroll")
public class PPCController {

    @Autowired
    private PPCService ppcService;

    @PostMapping("/processFiles")
    public String processFiles(@RequestParam("files") List<MultipartFile> files) {
        StringBuilder response = new StringBuilder();

        for (MultipartFile file : files) {
            try {
            	ppcService.processFiles(file.getInputStream());
                response.append("File Uploaded Successfully: ").append(file.getOriginalFilename()).append("\n");
                System.out.println(response);
            } catch (IOException e) {
                response.append("Failed to upload file: ").append(file.getOriginalFilename()).append(" - ").append(e.getMessage()).append("\n");
            }
        }

        return response.toString();
    }
}
