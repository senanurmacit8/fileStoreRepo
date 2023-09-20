package com.example.fileStoreProject.model;

import lombok.Data;

@Data
public class FileAddResponse {
    public FileAddResponse(String fileName) {
        this.fileName = fileName;
    }

    private String fileName;
}
