package com.example.ReferalProject.Service.Implementation;

import com.example.ReferalProject.Entity.User;
import com.example.ReferalProject.Repository.UserRepository;
import com.example.ReferalProject.Service.UserService;
import com.example.ReferalProject.dto.ReferralResponse;
import com.example.ReferalProject.dto.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User signup(SignupRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        String generatedReferralCode = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        User referrer = null;
        if (request.getReferralCode() != null && !request.getReferralCode().isEmpty()) {
            Optional<User> referrerOpt = userRepository.findByReferralCode(request.getReferralCode());
            if (referrerOpt.isPresent()) {
                referrer = referrerOpt.get();
            } else {
                throw new RuntimeException("Invalid referral code");
            }
        }

        User newUser = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .referralCode(generatedReferralCode)
                .referrer(referrer)
                .profileComplete(false)
                .createdAt(LocalDateTime.now())
                .build();

        return userRepository.save(newUser);
    }
    public User completeProfile(UUID userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.isProfileComplete()) {
            throw new RuntimeException("Profile already completed");
        }

        user.setProfileComplete(true);
        return userRepository.save(user);
    }
    @Override
    public List<ReferralResponse> getReferrals(UUID referrerId) {
        List<User> referredUsers = userRepository.findByReferrerId(referrerId);

        return referredUsers.stream()
                .map(user -> new ReferralResponse(
                        user.getName(),
                        user.getEmail(),
                        user.isProfileComplete()))
                .toList();
    }

    @Override
    public List<User> getReferralsByReferrer(UUID referrerId) {
        Optional<User> referrer = userRepository.findById(referrerId);
        if (referrer.isEmpty()) {
            throw new RuntimeException("Referrer not found");
        }
        return userRepository.findByReferrer(referrer.get());
    }

    @Override
    public void generateReferralReport(PrintWriter writer) {
        List<User> users = userRepository.findAll(); // make sure you autowired UserRepository

        // CSV Header
        writer.println("User ID,User Name,Email,Referred By,Is Profile Completed");

        for (User user : users) {
            String referredBy = user.getReferredBy() != null ? user.getReferredBy().toString() : "None";
            String line = String.format("%s,%s,%s,%s,%s",
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    referredBy,
                    user.isProfileComplete()
            );
            writer.println(line);
        }
    }


}
