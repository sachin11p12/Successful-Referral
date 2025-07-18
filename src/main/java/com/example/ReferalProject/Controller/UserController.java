package com.example.ReferalProject.Controller;

import com.example.ReferalProject.Entity.User;
import com.example.ReferalProject.Service.UserService;
import com.example.ReferalProject.dto.CompleteProfileRequest;
import com.example.ReferalProject.dto.ReferralResponse;
import com.example.ReferalProject.dto.SignupRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignupRequest request) {
        User createdUser = userService.signup(request);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/complete-profile")
    public ResponseEntity<User> completeProfile(@RequestBody CompleteProfileRequest request){
        User updatedUser = userService.completeProfile(request.getUserId());
        return ResponseEntity.ok(updatedUser);
    }

    // for referral summary (DTO version)
    @GetMapping("/referral-summary/{referrerId}")
    public ResponseEntity<List<ReferralResponse>> getReferralSummary(@PathVariable UUID referrerId) {
        List<ReferralResponse> referrals = userService.getReferrals(referrerId);
        return ResponseEntity.ok(referrals);
    }

    // for full referred users (entity version)
    @GetMapping("/referred-users/{referrerId}")
    public ResponseEntity<List<User>> getReferredUsers(@PathVariable UUID referrerId) {
        List<User> referrals = userService.getReferralsByReferrer(referrerId);
        return ResponseEntity.ok(referrals);
    }

    // csv download endpoint
    @GetMapping("/referral-report")
    public void generateReferralReport(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=referral-report.csv");

        userService.generateReferralReport(response.getWriter());
    }

}

