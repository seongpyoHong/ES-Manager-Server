package com.sphong.esmanager.cluster.repository;

import com.sphong.esmanager.cluster.domain.Credentials;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CredentialRepository extends MongoRepository<Credentials, ObjectId> {
    Optional<Credentials> findByProjectId(String projectId);
}
