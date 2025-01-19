package com.bechir.springbootaws.Services;


import com.bechir.springbootaws.Entities.Image;
import com.bechir.springbootaws.Repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Image_service {
    @Autowired
    private ImageRepository imageRepository;

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }
}
