package com.example.beautydiary.repositories;

import com.example.beautydiary.entities.PriceListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterAccountRepository extends JpaRepository<PriceListItem,Long> {
    List<PriceListItem> findAllByBeauticianId(Long id);
}
