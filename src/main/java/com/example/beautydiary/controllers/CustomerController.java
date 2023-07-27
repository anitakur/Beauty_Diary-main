package com.example.beautydiary.controllers;

import com.example.beautydiary.entities.Customer;
import com.example.beautydiary.entities.Reservation;
import com.example.beautydiary.services.CustomerService;
import com.example.beautydiary.services.MasterAccountService;
import com.example.beautydiary.services.ReservationService;
import com.example.beautydiary.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class CustomerController {
    private final CustomerService customerService;
    private final MasterAccountService mas;
    private final UserService userService;
    private final ReservationService reservationService;

    @Value("${profile.image.path}")
    private String profileImagePath;

    public CustomerController(CustomerService customerService, MasterAccountService mas, UserService userService, ReservationService reservationService) {
        this.customerService = customerService;
        this.mas = mas;
        this.userService = userService;
        this.reservationService = reservationService;
    }

    @GetMapping("/customer-account/{customerId}")
    public String displayCustomerAccount(@PathVariable("customerId") Long customerId, Model model,
                                         @CookieValue(value = "userId") String userIdFromCookie) {
        if (userIdFromCookie == null || !(userIdFromCookie.equals(customerId.toString()))) {
            return "redirect:/home";
        }
        Customer customer = customerService.getById(customerId);
        model.addAttribute("customer", customer);
        List<Reservation> reservationList = reservationService.getAllByCustomerId(customerId);
        model.addAttribute("reservationList", reservationList);
        return "/customer-account";
    }

    @PostMapping("/customer-account/{customerId}/updateProfile")
    public String updateProfile(@ModelAttribute("customer") Customer customer,
                                @PathVariable("customerId") Long customerId) throws Exception {
        try {
            customer.setId(customerId);
            userService.updateCustomer(customer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/customer-account/" + customerId;
    }

    @PostMapping("/customer-account/{customerId}/uploadImage")
    public String uploadImage(@PathVariable("customerId") Long customerId,
                              @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            String fileName = profileImagePath + "customers/" + customerId + ".jpg";
            mas.saveImage(imageFile, fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/customer-account/" + customerId;
    }

    @GetMapping("/customer-account/{customerId}/deleteReservation/{id}")
    public String deleteReservationById(@PathVariable("id") Long id, @PathVariable("customerId") Long customerId){
        reservationService.deleteById(id);
        return "redirect:/customer-account/" + customerId;
    }

}


