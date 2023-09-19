package com.example.fileStoreProject.Service.ServiceInpl;


import com.example.fileStoreProject.entity.FileEntity;
import com.example.fileStoreProject.model.FileInfo;
import com.example.fileStoreProject.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;


/**
Bu api dosyaları,
 isimlerini,
 uzantılarını bir rest endpoint aracılığı ile almalı,

 dosyayı
uygulamanın çalıştığı sunucu üzerinde dosya sistemine kayıt etmeli, dosyanın bulunduğu
path, boyutu, ismi, uzantısını ilişkisel bir veritabanında tutmalıdır.

 Gerektiğinde yine bir rest endpoint aracılığı ile dosyaların tüm bilgileri dönülmelidir.

 Dosya içeriği bir rest endpoint aracılığı ile byte arrray olarak dönülmelidir.

 Dosyalar rest endpoint üzerinden değiştirilip silinebilmelidir.

 Api erişimi jwt ile güvenli hale getirilmelidir.

 Swagger dökümantasyonu
 */
@Service
public class FileOperationServiceImpl {


    @Autowired
    FileRepository fileRepository;

    private void addNewFile(FileInfo fileInfo){

        String fileName = fileInfo.getFileName();
        File file = new File(fileName);

    }

    private List<FileEntity> listAllFiles(){
        return fileRepository.findAll();
    }







}
