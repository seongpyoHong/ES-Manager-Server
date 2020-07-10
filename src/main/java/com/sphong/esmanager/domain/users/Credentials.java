package com.sphong.esmanager.domain.users;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Document(collection = "credentials")
@Getter
public class Credentials {
    @Id
    private ObjectId id;

    @Column(name = "type", nullable = false)
    @JsonProperty("type")
    private String type;

    @Column(name = "project_id", nullable = false)
    @JsonProperty("project_id")
    private String projectId;

    @Column(name = "private_key_id", nullable = false)
    @JsonProperty("private_key_id")
    private String privateKeyId;

    @Column(name = "private_key", nullable = false)
    @JsonProperty("private_key")
    private String privateKey;

    @Column(name = "client_email", nullable = false)
    @JsonProperty("client_email")
    private String clientEmail;

    @Column(name = "client_id", nullable = false)
    @JsonProperty("client_id")
    private String clientId;

    @Column(name = "auth_uri", nullable = false)
    @JsonProperty("auth_uri")
    private String authUri;

    @Column(name = "token_uri", nullable = false)
    @JsonProperty("token_uri")
    private String tokenUri;

    @Column(name = "auth_provider_x509_cert_url", nullable = false)
    @JsonProperty("auth_provider_x509_cert_url")
    private String authProviderCertUrl;

    @Column(name = "client_x509_cert_url", nullable = false)
    @JsonProperty("client_x509_cert_url")
    private String clientCertUrl;

    public void setId() {
        this.id = new ObjectId();
    }

    @Builder
    public Credentials(String type, String projectId, String privateKeyId, String privateKey, String clientEmail, String clientId, String authUri, String tokenUri, String authProviderCertUrl, String clientCertUrl) {
        this.type = type;
        this.projectId = projectId;
        this.privateKeyId = privateKeyId;
        this.privateKey = privateKey;
        this.clientEmail = clientEmail;
        this.clientId = clientId;
        this.authUri = authUri;
        this.tokenUri = tokenUri;
        this.authProviderCertUrl = authProviderCertUrl;
        this.clientCertUrl = clientCertUrl;
    }
}