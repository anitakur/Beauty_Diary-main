package com.example.beautydiary.repositories;

import com.example.beautydiary.entities.Beautician;
import com.example.beautydiary.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface BeauticianRepository extends JpaRepository<Beautician,Long> {

    List<Beautician> findAllByCategoryId(Long id);
    Beautician findByEmail(String email);
}
