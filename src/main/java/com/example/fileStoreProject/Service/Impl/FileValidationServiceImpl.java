package com.example.fileStoreProject.service.Impl;

import com.example.fileStoreProject.service.IFileValidationService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;


/**
 * Dosya boyutu en fazla 5mb olmalı, uzantısı png, jpeg, jpg, docx, pdf, xlsx uzantılarından
 * biri olmalı, bu kurallara uymayan dosyalar kayıt edilmemeli ve sistem hata mesajı
 * dönmelidir.
 */
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

    public boolean isValidFileExtension(String filename) {
        boolean isValidFileExtension = false;
        List<String> validExtensionList = Arrays.asList("png", "jpeg", "jpg", "docx", "pdf", "xlsx");

        Optional<Object> fileExtension = Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));

        if (fileExtension.isPresent()) {
            isValidFileExtension = validExtensionList.stream().anyMatch((Predicate<? super String>) fileExtension.get());
        }

        return isValidFileExtension;
    }


}
