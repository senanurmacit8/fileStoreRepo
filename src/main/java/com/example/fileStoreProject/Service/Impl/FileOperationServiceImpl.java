package com.example.fileStoreProject.service.Impl;


import com.example.fileStoreProject.Controller.FileOperationsController;
import com.example.fileStoreProject.entity.FileEntity;
import com.example.fileStoreProject.repository.FileRepository;
import com.example.fileStoreProject.service.IFileOperationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Bu api dosyaları,
 * isimlerini,
 * uzantılarını bir rest endpoint aracılığı ile almalı,
 * <p>
 * dosyayı
 * uygulamanın çalıştığı sunucu üzerinde dosya sistemine kayıt etmeli,
 * dosyanın bulunduğu path, boyutu, ismi, uzantısını ilişkisel bir veritabanında tutmalıdır.
 * <p>
 * Gerektiğinde yine bir rest endpoint aracılığı ile dosyaların tüm bilgileri dönülmelidir.
 * <p>
 * Dosya içeriği bir rest endpoint aracılığı ile byte arrray olarak dönülmelidir.
 * <p>
 * Dosyalar rest endpoint üzerinden değiştirilip silinebilmelidir.
 * <p>
 * Api erişimi jwt ile güvenli hale getirilmelidir.
 * <p>
 * Swagger dökümantasyonu
 */
@Service
public class FileOperationServiceImpl implements IFileOperationService {
    Logger logger = Logger.getLogger(FileOperationsController.class);


    @Autowired
    FileRepository fileRepository;

    public ResponseEntity<FileEntity> createFile(FileEntity fileEntity) {
        logger.info("Create file started...");

        try {
            FileEntity newFileEntity = fileRepository
                    .save(new FileEntity(fileEntity.getFile_name(), 30, fileEntity.getFile_extension(),
                            fileEntity.getFile_path()));

            logger.info("File Created : " + fileEntity.getFile_name());

            return new ResponseEntity<>(newFileEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.info("Create file started...");
        }

    }

    public ResponseEntity<List<FileEntity>> listAllFiles() {
        logger.info("List all files started...");
        List<FileEntity> files = new ArrayList<FileEntity>();
        try {

            fileRepository.findAll().forEach(files::add);

            if (files.isEmpty()) {
                return new ResponseEntity<List<FileEntity>>(HttpStatus.NO_CONTENT);
            }

            logger.info("files are " + files.stream().map(FileEntity::getFile_name).collect(Collectors.joining()));
            return new ResponseEntity<>(files, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.info("List all files end...");
        }
    }

    public ResponseEntity<FileEntity> updateFileById(Long id, FileEntity fileEntity) {
        logger.info("Update file started...");

        Optional<FileEntity> tutorialData = fileRepository.findById(id);

        if (tutorialData.isPresent()) {
            FileEntity newFileEntity = tutorialData.get();
            newFileEntity.setFile_name(fileEntity.getFile_name());
            newFileEntity.setFile_extension(fileEntity.getFile_extension());
            newFileEntity.setFile_size(fileEntity.getFile_size());
            newFileEntity.setFile_path(fileEntity.getFile_path());


            logger.info("Update file end...");

            return new ResponseEntity<>(fileRepository.save(newFileEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<List<FileEntity>> findByFileName(String fileName) {
        logger.info("findByFileName started...");

        try {
            List<FileEntity> files = fileRepository.findByFileName(fileName);

            if (files.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(files, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.info("findByFileName end...");
        }
    }


}
