package com.example.fileStoreProject.Service;


public interface IFileValidationService {

    boolean isValidFileSize(String filename);

    boolean isValidFileExtension(String filename);


}
