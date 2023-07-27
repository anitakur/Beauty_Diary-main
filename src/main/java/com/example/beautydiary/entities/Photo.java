package com.example.beautydiary.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "file_name")
    private String fileName;
    @Lob
    @Column(length = 20 * 1024 * 1024)// 20Kb
    private byte[] data;
    @Column(name = "content_type")
    private String contentType;
    @ManyToOne
    private Beautician beautician;


    public String getBase64Data() {
        return Base64.getEncoder().encodeToString(data);
    }
}
