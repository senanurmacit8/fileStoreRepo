package com.example.fileStoreProject.Controller;


import com.example.fileStoreProject.Service.IFileOperationService;
import com.example.fileStoreProject.entity.FileEntity;
import com.example.fileStoreProject.model.AddFileRequest;
import com.example.fileStoreProject.model.FileInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
Dosya saklama ve listeleme işlemlerimizi yapabilmek için bir API oluşturulması
gerekmektedir.

 Dosya içeriği bir rest endpoint aracılığı ile byte arrray olarak dönülmelidir.

 Dosyalar rest endpoint üzerinden değiştirilip silinebilmelidir.
 */

@RestController
public class FileOperationsController {

    Logger logger = Logger.getLogger(FileOperationsController.class);

    @Autowired
    IFileOperationService fileOperationService;

    @RequestMapping(value = "/addNewFile", method = RequestMethod.POST)
    public void addNewFile(AddFileRequest fileAddRequest) {

        FileInfo fileInfo = new FileInfo();

        fileInfo.setFileName(fileAddRequest.getFileName());
        fileInfo.setFileExtension(fileAddRequest.getFileExtension());

        //fileOperationService.addNewFile(fileInfo);

        logger.info("new file added to database name as : " + fileInfo.getFileName());
    }

    @RequestMapping(value = "/listAllFiles", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FileEntity> listAllFiles(){


        return fileOperationService.listAllFiles();

    }

}
