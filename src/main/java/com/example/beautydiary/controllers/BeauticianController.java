package com.example.beautydiary.controllers;

import com.example.beautydiary.entities.*;
import com.example.beautydiary.services.BeauticianService;

import com.example.beautydiary.services.CustomerService;
import com.example.beautydiary.services.MasterAccountService;
import com.example.beautydiary.services.PhotoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;



@Controller
public class BeauticianController {
    private BeauticianService beauticianService;
    private MasterAccountService mas;
    private PhotoService photoService;



    public BeauticianController(BeauticianService beauticianService, MasterAccountService mas,
                                PhotoService photoService, CustomerService customerService) {
        this.beauticianService = beauticianService;
        this.mas = mas;
        this.photoService = photoService;
    }

    @GetMapping("/categories/{categoryId}/beauticians")
    public String findAllByCategoryId(@PathVariable("categoryId") Long categoryId, Model model){
        List<Beautician> beauticians = beauticianService.findAllByCategoryId(categoryId);
        model.addAttribute("beauticians", beauticians);
        return "beauticians";
    }

    @GetMapping("/beauticians/{id}")
    public String viewBeauticianProfile(@PathVariable("id") Long id, Model model,
                                        @CookieValue("userType") String loggedInUserType) {
        Beautician beautician = beauticianService.getById(id);
        model.addAttribute("beautician", beautician);
        var reservation = new Reservation();
        reservation.setBeautician(beautician);
        List<PriceListItem> itemList = mas.getAllByBeauticianId(id);
        PriceListItem item = new PriceListItem();
        item.setBeautician(beautician);
        List<Photo> photoList = photoService.findAllByBeauticianId(beautician.getId());
        model.addAttribute("itemList", itemList);
        model.addAttribute("item", item);
        model.addAttribute("reservation", reservation);
        model.addAttribute("photoList", photoList);
        model.addAttribute("loggedInUserType", loggedInUserType);
        return "beautician";
    }

}
