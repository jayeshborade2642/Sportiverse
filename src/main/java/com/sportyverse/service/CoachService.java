package com.sportyverse.service;

import com.sportyverse.dto.CoachDto;
import com.sportyverse.dto.CoachLoginDto;
import com.sportyverse.response.CoachLoginResponse;

import java.util.List;

public interface CoachService {

    CoachDto createCoach(CoachDto coachDto);

    CoachDto getCoachById(Long coachId);

    List<CoachDto> getAllCoaches();

    CoachDto getCoachBySport(String sport);

    CoachDto updateCoach(Long coachId, CoachDto updatedCoach);

    void deleteCoach(Long coachId);

    CoachLoginResponse loginCoach(CoachLoginDto coachLoginDto);
}
