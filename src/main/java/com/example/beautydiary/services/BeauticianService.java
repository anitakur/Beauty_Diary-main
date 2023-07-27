package com.example.beautydiary.services;

import com.example.beautydiary.entities.Beautician;
import com.example.beautydiary.repositories.BeauticianRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeauticianService {
    private  BeauticianRepository beauticianRepository;

    public BeauticianService(BeauticianRepository beauticianRepository) {
        this.beauticianRepository = beauticianRepository;
    }

    public List<Beautician> findAllByCategoryId(Long id){

      return beauticianRepository.findAllByCategoryId(id);
    }
    public Beautician getById(Long id){
        return beauticianRepository.getReferenceById(id);
    }

   public Beautician saveBeautician(Beautician beautician){
        return beauticianRepository.save(beautician);
   }
}
