package com.example.fileStoreProject.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "t_file_info")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotNull(message = "File name cannot be null")
    private String file_name;

    //@Max(value = 50000, message = "File size should not be greater than 5 MB")
    private int file_size;

    private String file_extension;

    private String file_path;

}
