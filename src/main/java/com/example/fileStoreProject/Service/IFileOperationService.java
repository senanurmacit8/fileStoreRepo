package com.example.fileStoreProject.Service;


import com.example.fileStoreProject.entity.FileEntity;
import com.example.fileStoreProject.model.FileInfo;

import java.util.List;

public interface IFileOperationService {

    void addNewFile(FileInfo fileInfo);

    List<FileEntity> listAllFiles();


}
