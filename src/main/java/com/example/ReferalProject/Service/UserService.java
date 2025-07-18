package com.example.ReferalProject.Service;

import com.example.ReferalProject.Entity.User;
import com.example.ReferalProject.dto.ReferralResponse;
import com.example.ReferalProject.dto.SignupRequest;

import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

public interface UserService {
    User signup(SignupRequest request);
    User completeProfile(UUID userId);
    List<ReferralResponse> getReferrals(UUID referrerId);
    List<User> getReferralsByReferrer(UUID referrerId);
    void generateReferralReport(PrintWriter writer);



}
