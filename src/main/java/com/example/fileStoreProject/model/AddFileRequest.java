package com.example.fileStoreProject.model;


import lombok.Data;

@Data
public class AddFileRequest {

    private String fileName;
    private String fileExtension;

}
