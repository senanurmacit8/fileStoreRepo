package com.example.fileStoreProject.service.Impl;

import com.example.fileStoreProject.service.IFileValidationService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
public class FileValidationServiceImpl implements IFileValidationService {

    public boolean isValidFileSize(String filename) {
        Path path = Paths.get(filename);
        try {
            long bytes = Files.size(path);
            if (bytes > 5000000L) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean isValidFileExtension(String fileExtension) {
        boolean isValidFileExtension = false;
        List<String> validExtensionList = Arrays.asList("png", "jpeg", "jpg", "docx", "pdf", "xlsx");

        if (null != fileExtension) {
            isValidFileExtension = validExtensionList.parallelStream().anyMatch(s -> s.equalsIgnoreCase(fileExtension));
        }

        return isValidFileExtension;
    }


}
