package com.example.beautydiary.services;

import com.example.beautydiary.entities.Beautician;
import com.example.beautydiary.entities.Photo;
import com.example.beautydiary.repositories.PhotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class PhotoService {
    private PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }


    public List<Photo> findAllByBeauticianId(Long id) {
        return photoRepository.findAllByBeauticianId(id);
    }

    public Photo savePhoto(String fileName, String contentType, byte[] data, Long beauticianId) {
        Photo photo = new Photo();
        photo.setFileName(fileName);
        photo.setData(data);
        photo.setContentType(contentType);
        photo.setBeautician(new Beautician());
        photo.getBeautician().setId(beauticianId);
        return photoRepository.save(photo);
    }

    public void deletePhotoById(Long id){
        photoRepository.deleteById(id);
    }
}
