package com.sportyverse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoachDto {

    private Long coachId;
    private String coachFirstName;
    private String coachLastName;
    private String sport;
    private String description;
    private String gender;
    private String awards;
    private Double fees;
    private String duration;
    private String email;
    private String password;
    private String coachImage;

}
