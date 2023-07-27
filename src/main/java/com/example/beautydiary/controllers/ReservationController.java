package com.example.beautydiary.controllers;
import com.example.beautydiary.entities.Customer;
import com.example.beautydiary.entities.Reservation;
import com.example.beautydiary.services.ReservationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReservationController {
    private ReservationService reservationService;
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping(path = "/reservation")
    public String addReservation(@ModelAttribute Reservation reservation, HttpSession session,
                                 @CookieValue("userId") Long customerId) {
        reservation.setCustomer(new Customer());
        reservation.getCustomer().setId(customerId);
        reservationService.saveReservation(reservation);
        session.setAttribute("reservation", reservation);
        return "redirect:/reservation-result";
    }

    @GetMapping(path = "/reservation-result")
    public String showReservationResultPage(HttpSession session, Model model) {
        Reservation reservation =  (Reservation) session.getAttribute("reservation");
        if(reservation != null) {
            model.addAttribute("reservation", reservation);
            session.setAttribute("reservation", null);
            return "reservation-result";
        } else {
            return "redirect:/home";
        }
    }

    @GetMapping("/reservations-list")
    public String showReservationListPage() {
        return "reservations-list";
    }

    //beauticians/id/reservations
    @GetMapping("/reservations-list/{beauticianId}")
    public String getAll(@PathVariable("beauticianId") Long beauticianId, Model model){
        List<Reservation> reservations = reservationService.getAllByBeauticianId(beauticianId);
        model.addAttribute("reservations", reservations);
        return "reservations-list";
    }

    @GetMapping("/beautician/{beauticianId}/deleteReservation/{id}")
    public String deleteReservationsById(@PathVariable("id") Long id, @PathVariable("beauticianId") Long beauticianId){
       reservationService.deleteById(id);
       return "redirect:/reservations-list/"+ beauticianId;
    }


}
