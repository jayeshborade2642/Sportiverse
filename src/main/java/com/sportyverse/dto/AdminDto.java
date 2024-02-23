package com.sportyverse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
    private Long adminId;
    private String admin_firstName;
    private String admin_lastName;
    private String admin_email;
    private String admin_password;
}
