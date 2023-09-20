package com.example.fileStoreProject.Controller;


import com.example.fileStoreProject.model.FileAddResponse;
import com.example.fileStoreProject.model.FileInfo;
import com.example.fileStoreProject.service.IFileOperationService;
import com.example.fileStoreProject.entity.FileEntity;
import com.example.fileStoreProject.service.Impl.FileOperationBusinessServiceImpl;
import com.example.fileStoreProject.service.Impl.FileStorageServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@RestController
public class FileOperationsController {


    @Autowired
    FileOperationBusinessServiceImpl fileOperationBusinessService;


    @GetMapping("/listUploadedFiles")
    public String listUploadedFiles(Model model) throws IOException {
        fileOperationBusinessService.listUploadedFiles(model);
        return "uploadForm";
    }

    @GetMapping("/serveFile/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        return fileOperationBusinessService.getFile(filename);
    }

    @GetMapping("/serveFileAsByteArray/{filename:.+}")
    @ResponseBody
    public ResponseEntity<byte[]> serveFileAsByteArray(@PathVariable String filename) throws IOException {
        return fileOperationBusinessService.getFileContentAsByteArray(filename);
    }


    @PostMapping("/uploadFile")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        String responseMessage = fileOperationBusinessService.handleFileUpload(file, redirectAttributes);

        return responseMessage;
    }

    @PostMapping("/deleteAllUploadedFiles")
    public void deleteAllUploadedFiles() {
        fileOperationBusinessService.deleteAll();
    }

}
