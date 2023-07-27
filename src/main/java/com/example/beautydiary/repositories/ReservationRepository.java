package com.example.beautydiary.repositories;


import com.example.beautydiary.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository< Reservation, Long> {
       List<Reservation> findAllByBeauticianId(Long id);
       List<Reservation> findAllByCustomerId(Long id);
}
