package com.example.fileStoreProject.service;


import com.example.fileStoreProject.entity.FileEntity;
import org.springframework.http.ResponseEntity;

public interface IFileOperationService {

    ResponseEntity<FileEntity> createFile(FileEntity fileEntity);

    ResponseEntity<FileEntity> updateFileById(Long id, FileEntity fileEntity);

}
