package com.example.beautydiary.services;

import com.example.beautydiary.entities.PriceListItem;
import com.example.beautydiary.repositories.MasterAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

import java.nio.file.Paths;
import java.util.List;

@Service
public class MasterAccountService {
    private MasterAccountRepository masterAccountRepository;

    @Autowired
    public MasterAccountService(MasterAccountRepository masterAccountRepository) {
        this.masterAccountRepository = masterAccountRepository;
    }

    public PriceListItem addPriceListItem(PriceListItem priceListItem) {
        return masterAccountRepository.save(priceListItem);
    }

    public void deletePriceListItemById(Long id){
        masterAccountRepository.deleteById(id);
    }

    public List<PriceListItem> getAllByBeauticianId(Long id) {
        return masterAccountRepository.findAllByBeauticianId(id);
    }

    public void saveImage(MultipartFile imageFile, String fileName) throws IOException {
        imageFile.transferTo(Paths.get(fileName));
    }
}