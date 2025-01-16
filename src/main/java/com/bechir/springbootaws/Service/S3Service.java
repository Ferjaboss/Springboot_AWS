package com.bechir.springbootaws.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;


import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Log4j
@Service
public class S3Service {
@Autowired
    private AmazonS3 s3client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public S3Service(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    public void uploadFile(String keyName, MultipartFile file) throws IOException {
        var putObjectResult = s3client.putObject(bucketName, keyName, file.getInputStream(), null);
        System.out.println(putObjectResult.getMetadata());
    }

    public S3Object getFile(String keyName) {
        return s3client.getObject(bucketName, keyName);
    }

}
