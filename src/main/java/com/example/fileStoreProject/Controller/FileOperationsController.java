package com.example.fileStoreProject.Controller;


import com.example.fileStoreProject.model.FileInfo;
import com.example.fileStoreProject.service.IFileOperationService;
import com.example.fileStoreProject.entity.FileEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;


/**
Dosya saklama ve listeleme işlemlerimizi yapabilmek için bir API oluşturulması
gerekmektedir.

 Dosya içeriği bir rest endpoint aracılığı ile byte arrray olarak dönülmelidir.

 Dosyalar rest endpoint üzerinden değiştirilip silinebilmelidir.
 */

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
public class FileOperationsController {

    Logger logger = Logger.getLogger(FileOperationsController.class);

   @Autowired(required = false)
   IFileOperationService fileOperationService;

    @PostMapping("/createFile")
    public ResponseEntity<FileEntity> createFile(@RequestBody FileInfo fileInfo) {

        FileEntity fileEntity = new FileEntity();
        fileEntity.setFile_name(fileInfo.getFileName());
        fileEntity.setFile_extension(fileInfo.getFileExtension());

        return fileOperationService.createFile(fileEntity);
    }

    @GetMapping(value = "/listAllFiles" )
    public ResponseEntity<List<FileEntity>> listAllFiles(){
        return fileOperationService.listAllFiles();
    }

}
