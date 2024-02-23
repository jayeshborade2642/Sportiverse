package com.sportyverse.mapper;

import com.sportyverse.dto.CoachDto;
import com.sportyverse.entity.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CoachMapper {

    @Autowired
    private PasswordEncoder coachPasswordEncoder;

    public CoachDto mapToCoachDto(Coach coach){
        return new CoachDto(
              coach.getCoachId(),
              coach.getCoachFirstName(),
              coach.getCoachLastName(),
              coach.getSport(),
              coach.getDescription(),
              coach.getGender(),
              coach.getAwards(),
              coach.getFees(),
              coach.getDuration(),
              coach.getEmail(),
              coach.getPassword(),
              coach.getCoachImage()
        );
    }

    public Coach mapToCoach(CoachDto coachDto){
        return new Coach(
                coachDto.getCoachId(),
                coachDto.getCoachFirstName(),
                coachDto.getCoachLastName(),
                coachDto.getSport(),
                coachDto.getDescription(),
                coachDto.getGender(),
                coachDto.getAwards(),
                coachDto.getFees(),
                coachDto.getDuration(),
                coachDto.getEmail(),
                this.coachPasswordEncoder.encode(coachDto.getPassword()),
                coachDto.getCoachImage()
        );
    }
}
