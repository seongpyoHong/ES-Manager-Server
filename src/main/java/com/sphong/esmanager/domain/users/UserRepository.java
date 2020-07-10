package com.sphong.esmanager.domain.users;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<Users, ObjectId> {
    Optional<Users> findByEmail(String email);
}

