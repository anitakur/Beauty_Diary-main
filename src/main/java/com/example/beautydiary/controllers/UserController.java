package com.example.beautydiary.controllers;


import com.example.beautydiary.entities.Beautician;
import com.example.beautydiary.entities.User;
import com.example.beautydiary.services.BeauticianService;
import com.example.beautydiary.services.CustomerService;
import com.example.beautydiary.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class UserController {
    private final UserService userService;
    private final BeauticianService beauticianService;
    private final CustomerService customerService;

    @Autowired
    public UserController(UserService userService, BeauticianService beauticianService, CustomerService customerService) {
        this.userService = userService;
        this.beauticianService = beauticianService;
        this.customerService = customerService;
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String handleUserRegistration(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        try {
            userService.createUser(user);
        } catch (Exception e) {
            model.addAttribute("message", "signup_failed");
            model.addAttribute("error", e.getMessage());
            model.addAttribute("user", new User());
            return "register";
        }
        return "redirect:login?message=signup_success";
    }

    @GetMapping("/login")
    public String showLoginPage(
            @RequestParam(name = "message", required = false) String message,
            Model model) {
        model.addAttribute("message", message);
        return "login";
    }

    @PostMapping("/login")
    public String handleUserLogin(User user, HttpServletResponse response, Model model) {
        try {
            User loggedInUser = userService.verifyUser(user);
            Cookie userIdCookie = new Cookie("userId", loggedInUser.getId().toString());
            userIdCookie.setPath("/");
            userIdCookie.setMaxAge(3600); // 1 hour

            Cookie userTypeCookie = new Cookie("userType", loggedInUser.getUserType());
            userTypeCookie.setPath("/");
            userTypeCookie.setMaxAge(3600); // 1 hour

            Cookie userIsLoggedInCookie = new Cookie("userIsLoggedIn", "true");
            userIsLoggedInCookie.setPath("/");
            userIsLoggedInCookie.setMaxAge(3600); // 1 hour

            response.addCookie(userIdCookie);
            response.addCookie(userIsLoggedInCookie);
            response.addCookie(userTypeCookie);

            model.addAttribute("user", loggedInUser);
            if (Objects.equals(loggedInUser.getUserType(), "beautician")) {
                return "redirect:/master-account/" + loggedInUser.getId();
            }
           else if (Objects.equals(loggedInUser.getUserType(), "customer")) {
                return "redirect:/customer-account/" + loggedInUser.getId();
            }
        } catch (Exception e) {
            return "redirect:login?message=login_failed&error=" + e.getMessage();
        }
        return "redirect:/home";
    }
    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie userIdCookie = new Cookie("userId", "");
        userIdCookie.setPath("/");
        userIdCookie.setMaxAge(0);

        Cookie userIsLoggedInCookie = new Cookie("userIsLoggedIn", "false");
        userIsLoggedInCookie.setPath("/");
        userIsLoggedInCookie.setMaxAge(0);

        response.addCookie(userIdCookie);
        response.addCookie(userIsLoggedInCookie);

        return "redirect:/home?message=logout_success";
    }

    @GetMapping("/my-account")
    public String showMyAccountNav(@CookieValue("userType") String loggedInUserType,
                                   @CookieValue("userId") Long userId, Model model){
        model.addAttribute("loggedInUserType",loggedInUserType);
        if(loggedInUserType.equals("beautician")){
            return "redirect:/master-account/"+ userId;
        } else if (loggedInUserType.equals("customer")) {
            return "redirect:/customer-account/" + userId;
        }
       return "/home";
    }



}
