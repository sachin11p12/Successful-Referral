package com.example.ReferalProject.Repository;

import com.example.ReferalProject.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
//    Optional<User> findByReferralCode(String referralCode);
    Optional<User> findByReferralCode(String referralCode);
    Optional<User> findByEmail(String email);

    List<User> findByReferrerId(UUID referrerId);   //This will return all users where the referrer_id matches the given ID.
// for referre
    List<User> findByReferrer(User referrer);


}
