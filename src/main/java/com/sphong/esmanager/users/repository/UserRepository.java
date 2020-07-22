package com.sphong.esmanager.users.repository;

import com.sphong.esmanager.users.domain.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<Users, ObjectId> {
    Optional<Users> findByEmail(String email);
}

