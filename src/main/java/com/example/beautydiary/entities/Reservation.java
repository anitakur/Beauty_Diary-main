package com.example.beautydiary.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;



@Entity
@Data
@Table(name = "service_reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name")
    private String name;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date date;
    private String time;
    private String phoneNumber;
    @OneToOne
    private Beautician beautician;
    @OneToOne
    private Customer customer;
    @Transient
    private List<String> times = (List.of("09:00", "10:00", "11:00","12:00","13:00","14:00","15:00","16:00","17:00"));
}
