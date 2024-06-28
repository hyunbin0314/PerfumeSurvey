package com.example.Perfume.controller;

import com.example.Perfume.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @PostMapping("/upload")
    public String uploadExcelFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Please select a file!";
        }

        try {
            excelService.saveExcelData(file);
            return "File uploaded and data saved successfully!";
        } catch (Exception e) {
            return "File upload failed: " + e.getMessage();
        }
    }

}
