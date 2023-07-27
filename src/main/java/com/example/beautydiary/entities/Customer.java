package com.example.beautydiary.entities;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Customer")
public class Customer extends User {
    private String address;

    public String getUserType() {
        return "customer";
    }
}
