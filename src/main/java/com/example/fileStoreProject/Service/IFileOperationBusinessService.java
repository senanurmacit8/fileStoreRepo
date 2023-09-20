package com.example.fileStoreProject.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface IFileOperationBusinessService {

    String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes);

}
