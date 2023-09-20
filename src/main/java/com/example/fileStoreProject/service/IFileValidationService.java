package com.example.fileStoreProject.service;


public interface IFileValidationService {

    boolean isValidFileSize(String filename);

    boolean isValidFileExtension(String filename);


}
