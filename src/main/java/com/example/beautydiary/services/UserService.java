package com.example.beautydiary.services;

import com.example.beautydiary.entities.Beautician;
import com.example.beautydiary.entities.Customer;
import com.example.beautydiary.entities.User;
import com.example.beautydiary.repositories.BeauticianRepository;
import com.example.beautydiary.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private BeauticianRepository beauticianRepository;
    private CustomerRepository customerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
     

    @Autowired
    public UserService(BeauticianRepository beauticianRepository,
                       CustomerRepository customerRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.beauticianRepository = beauticianRepository;
        this.customerRepository = customerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void createUser(User user) throws Exception {
        User existing = beauticianRepository.findByEmail(user.getEmail());

        if (existing == null) {
            existing = customerRepository.findByEmail(user.getEmail());
        }
        if (existing != null) {
            throw new Exception("User with email already exists.");
        }

        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        if (user.getUserType().equals("beautician")) {
        beauticianRepository.save(user.getBeautician());
        } else if (user.getUserType().equals("customer"))
          customerRepository.save(user.getCustomer()) ;
    }

    public User verifyUser(User user) throws Exception {
        User foundUser = beauticianRepository.findByEmail(user.getEmail());

        if (foundUser == null) {
            foundUser = customerRepository.findByEmail(user.getEmail());
        }
        if (foundUser == null) {
            throw new Exception("Username incorrect");
        }
        if(!bCryptPasswordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            throw new Exception("Password incorrect");
        }
        return foundUser;
    }

    public void updateBeautician(Beautician beautician) {
        beauticianRepository.save(beautician);
    }

    public void updateCustomer(Customer customer){
        customerRepository.save(customer);
    }
}
