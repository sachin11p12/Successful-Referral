package com.example.ReferalProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReferralResponse {

    private String name;
    private String email;
    private boolean profileComplete;
}
