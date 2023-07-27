package com.example.beautydiary.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;


    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Full name must contain only letters")
    private String fullName;


    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email address")
    @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}", message = "Invalid email format")
    @Column(unique = true)
    private String email;


    @Size(min = 8, max = 8, message = "Phone number min 8 and max 8 characters long ")
    @Pattern(regexp = "\\d{8}", message = "Phone number must contain only digits")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotBlank(message = "Password is required")
    private String password;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    private String userType;

    public Beautician getBeautician() {
        Beautician beautician = new Beautician();
        beautician.setEmail(email);
        beautician.setPhoneNumber(phoneNumber);
        beautician.setFullName(fullName);
        beautician.setPassword(password);
        beautician.setUserType(userType);
        return beautician;
    }

    public Customer getCustomer(){
        Customer customer = new Customer();
        customer.setFullName(fullName);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        customer.setPassword(password);
        customer.setUserType(userType);
        return customer;
    }

}
