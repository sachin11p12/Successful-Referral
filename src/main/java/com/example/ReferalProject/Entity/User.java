package com.example.ReferalProject.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "referral_code", unique = true, nullable = false)
    private String referralCode;

    // Self-referencing relationship: who referred this user
    @ManyToOne
    @JoinColumn(name = "referrer_id")
    private User referrer;

    @Column(name = "profile_complete")
    private boolean profileComplete;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Set default values in lifecycle callbacks (optional, better than inline init)
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.profileComplete = false;
    }

    // Optional: For CSV report or DTO mapping
    public UUID getReferredBy() {
        return referrer != null ? referrer.getId() : null;
    }
}
