package com.bechir.springbootaws.Repositories;



import com.bechir.springbootaws.Entities.Image;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends MongoRepository<Image, ObjectId> {
    Optional<Image> findByName(String name);
}
