package com.example.beautydiary.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "Beautician")
public class Beautician extends User {
    private String address;
    @ManyToOne
    private Category category;
    private String aboutMe;


    public String getUserType() {
        return "beautician";
    }

    public String getShortAboutMe() {
        int maxLength = 100;
        if (aboutMe == null) return "";
        if (aboutMe.length() <= maxLength) {
            return aboutMe;
        } else {
            return aboutMe.substring(0, maxLength) + "...";
        }
    }
}


