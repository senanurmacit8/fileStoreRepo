package com.example.fileStoreProject.service.Impl;


import com.example.fileStoreProject.config.StorageProperties;
import com.example.fileStoreProject.entity.FileEntity;
import com.example.fileStoreProject.service.IFileOperationBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class FileOperationBusinessServiceImpl implements IFileOperationBusinessService {

    Logger logger = Logger.getLogger(String.valueOf(FileOperationBusinessServiceImpl.class));

    private final Path rootLocation;

    private FileStorageServiceImpl fileStorageService;
    private FileOperationServiceImpl fileOperationService;
    private FileValidationServiceImpl fileValidationService;

    @Autowired
    public FileOperationBusinessServiceImpl(StorageProperties properties, FileStorageServiceImpl fileStorageService,
                                            FileOperationServiceImpl fileOperationService) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.fileStorageService = fileStorageService;
        this.fileOperationService = fileOperationService;
    }

    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        int fileNameIndex = file.getOriginalFilename().indexOf(".");
        String fileExtension = file.getOriginalFilename().substring(fileNameIndex + 1);

        boolean isExtensionValid = fileValidationService.isValidFileExtension(fileExtension);

        if (isExtensionValid) {
            fileStorageService.store(file);
            redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");

            createFile(file, fileNameIndex);
            logger.info("file created in h2 before sent relational database name as :" + file.getOriginalFilename());
        }

        return redirectAttributes.getFlashAttributes().toString();
    }

    public ResponseEntity<Resource> getFile(String filename) {

        Resource file = (Resource) fileStorageService.loadAsResource(filename);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    public ResponseEntity<byte[]> getFileContentAsByteArray(String filename) throws IOException {

        Resource file = (Resource) fileStorageService.loadAsResource(filename);
        return ResponseEntity.ok().body(file.getContentAsByteArray());
    }

    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", fileStorageService.loadAll().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(FileOperationBusinessServiceImpl.class,
                                "getFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    public String deleteAll() {
        fileStorageService.deleteAll();
        return "all files are deleted";
    }


    private void createFile(MultipartFile file, int fileNameIndex) {
        ;
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFile_name(file.getOriginalFilename().substring(0, fileNameIndex));
        fileEntity.setFile_extension(file.getOriginalFilename().substring(fileNameIndex + 1));
        fileEntity.setFile_size(Integer.valueOf(String.valueOf(file.getSize())));

        fileOperationService.createFile(fileEntity);
        logger.info("file created in h2 before sent relational database name as :" + file.getOriginalFilename());

    }
}
