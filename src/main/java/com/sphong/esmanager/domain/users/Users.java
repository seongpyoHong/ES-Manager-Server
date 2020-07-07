package com.sphong.esmanager.domain.users;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;

@Document("users")
@Getter
public class Users {
    @Id
    private ObjectId id;
    private String email;
    private String password;
    private String projectName;
    private String dockerRegistry;
    @DBRef
    private Credentials credentials;

    @Builder
    public Users(String email, String password, String projectName, String dockerRegistry, Credentials credentials) {
        this.id = new ObjectId();
        this.email = email;
        this.password = password;
        this.projectName = projectName;
        this.dockerRegistry = dockerRegistry;
        this.credentials = credentials;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", projectName='" + projectName + '\'' +
                ", dockerRegistry='" + dockerRegistry + '\'' +
                ", credentials=" + credentials.getClientEmail() +
                '}';
    }
}
