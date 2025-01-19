package com.bechir.springbootaws;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.bechir.springbootaws.Services.S3Service;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Log4j2
public class SpringbootWithAwsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWithAwsApplication.class, args);
    }
    @Bean
    public ApplicationRunner applicationRunner(S3Service s3Service){
        return args -> {
            log.info("Uploading file to S3");

            try {
                var s3Object = s3Service.getFile("jvm.png");
                System.out.println(s3Object.getKey());
            } catch (AmazonS3Exception e) {
                System.out.println(e.getMessage());
            }
        };
    }
}
