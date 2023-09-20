package com.example.fileStoreProject.service;


import com.example.fileStoreProject.entity.FileEntity;
import com.example.fileStoreProject.model.FileInfo;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface IFileOperationService {

    ResponseEntity<FileEntity> createFile(FileEntity fileEntity);

    ResponseEntity<List<FileEntity>> listAllFiles();

     ResponseEntity<FileEntity> updateFileById(Long id,FileEntity fileEntity);

    ResponseEntity<List<FileEntity>> findByFileName(String fileName) ;

    }
