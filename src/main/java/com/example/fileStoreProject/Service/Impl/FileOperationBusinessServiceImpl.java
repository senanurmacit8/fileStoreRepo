package com.example.fileStoreProject.service.Impl;


import com.example.fileStoreProject.config.StorageProperties;
import com.example.fileStoreProject.model.FileInfo;
import com.example.fileStoreProject.service.IFileOperationBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Logger;

@Service
public class FileOperationBusinessServiceImpl implements IFileOperationBusinessService {

    Logger logger = Logger.getLogger(String.valueOf(FileOperationBusinessServiceImpl.class));

    private final Path rootLocation;

    @Autowired
    public FileOperationBusinessServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    public Byte[] getFileContent(FileInfo fileInfo) {
        logger.info("Create Otf File start...");

        String fileLocation = fileInfo.getFilePath() + fileInfo.getFileName();
        File file = new File(fileInfo.getFilePath());



        logger.info("Create Otf File end...");

        return new Byte[0];
    }

    public byte[] convertFileContentToByteArray(String content) throws IOException {
        logger.info("Converted file as Byte[] file start...");
        return content.getBytes();
    }


    @Override
    public void storeFileWithContext(MultipartFile file) {

        try {
            if (file.isEmpty()) {
                throw new Exception("Failed to store empty file.");
            }

            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new Exception("Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
