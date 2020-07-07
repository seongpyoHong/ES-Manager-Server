package com.sphong.esmanager.domain.users;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CredentialRepository extends MongoRepository<Credentials, ObjectId> {
    Optional<Credentials> findByProjectId(String projectId);
}
