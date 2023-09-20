package com.example.fileStoreProject.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "t_file_info")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public FileEntity(String file_name, int file_size, String file_extension, String file_path) {
        this.file_name = file_name;
        this.file_size = file_size;
        this.file_extension = file_extension;
        this.file_path = file_path;
    }

    @Column(name = "file_name")
    private String file_name;

    @Column(name = "file_size")
    private int file_size;

    @Column(name = "file_extension")
    private String file_extension;

    @Column(name = "file_path")
    private String file_path;

}
