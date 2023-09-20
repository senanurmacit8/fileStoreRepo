package com.example.fileStoreProject.service;

import io.github.classgraph.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface IStorageService {

    void init();

    void store(MultipartFile file);

    Stream<Path> loadAll() throws IOException;

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

}

