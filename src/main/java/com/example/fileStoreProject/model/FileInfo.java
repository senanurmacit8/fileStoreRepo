package com.example.fileStoreProject.model;

import lombok.Data;

@Data
public class FileInfo {
    private String fileName;
    private String fileSize;
    private String fileExtension;
    private String filePath;

}
