package com.example.authentication_service.model;

import jakarta.persistence.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;

@Table(name = "session")
@Entity
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id", nullable = false)
    private Integer user_id;

    @Column(nullable = false, length = 255)
    private String token;

    @Column(nullable = false)
    private long expires;

    public Integer getUser_id() {
        return user_id;
    }

    public String getToken() {
        return token;
    }

    public Long getId() {
        return id;
    }

    public long getExpires() {
        return expires;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
