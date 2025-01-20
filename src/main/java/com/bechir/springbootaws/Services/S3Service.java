package com.bechir.springbootaws.Services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;


import com.bechir.springbootaws.Entities.Image;
import com.bechir.springbootaws.Repositories.ImageRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Optional;

@Log4j2
@Service
public class S3Service {

    @Autowired
    private AmazonS3 s3client;
    @Autowired
    private ImageRepository imageRepository;


    @Value("${aws.s3.bucket}")
    private String bucketName;

    public S3Service(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    public void uploadFile(String keyName, MultipartFile file) throws IOException {
        String ImageName = System.currentTimeMillis()+keyName;
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setUrl(ImageName);
        imageRepository.save(image);
        var putObjectResult = s3client.putObject(bucketName, ImageName, file.getInputStream(), null);
        log.info("File uploaded with ETag: {}", putObjectResult.getETag());
    }

    public S3Object getFile(String keyName) {
        return s3client.getObject(bucketName, keyName);
    }


    public void deleteFile(String keyName) {
        System.out.println(keyName);
        Optional<Image> image = imageRepository.findByUrl(keyName);
        if (image.isEmpty()) {
            throw new RuntimeException("Image not found");
        }
        imageRepository.delete(image.get());
        s3client.deleteObject(bucketName, keyName);
    }

}
