package com.sportyverse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coach")
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coachId;

    @Column(name = "coach_firstname")
    private String coachFirstName;

    @Column(name = "coach_lastname")
    private String coachLastName;

    @Column(name = "coach_sport")
    private String sport;

    @Column(name = "coach_description")
    private String description;

    @Column(name = "gender")
    private String gender;

    @Column(name = "awards")
    private String awards;

    @Column(name = "fees")
    private Double fees;

    @Column(name = "duration")
    private String duration;

    @Column(name = "coach_email")
    private String email;

    @Column(name = "coach_password")
    private String password;

    @Column(name = "coach_image")
    private String coachImage;
}
