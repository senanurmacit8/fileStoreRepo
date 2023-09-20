package com.example.fileStoreProject.repository;

import com.example.fileStoreProject.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {

     List<FileEntity> findAll();
}
