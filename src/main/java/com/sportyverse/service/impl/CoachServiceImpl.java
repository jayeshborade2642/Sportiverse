package com.sportyverse.service.impl;

import com.sportyverse.dto.CoachDto;
import com.sportyverse.dto.CoachLoginDto;
import com.sportyverse.entity.Coach;
import com.sportyverse.exception.ResourceNotFoundException;
import com.sportyverse.mapper.CoachMapper;
import com.sportyverse.repository.CoachRepository;
import com.sportyverse.response.CoachLoginResponse;
import com.sportyverse.service.CoachService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CoachServiceImpl implements CoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private final PasswordEncoder coachPasswordEncoder;

    @Autowired
    private CoachMapper coachMapper;

    @Override
    public CoachDto createCoach(CoachDto coachDto) {
        Coach coach = coachMapper.mapToCoach(coachDto);
        Coach savedCoach = coachRepository.save(coach);
        return coachMapper.mapToCoachDto(savedCoach);
    }

    @Override
    public CoachDto getCoachById(Long coachId) {
        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Coach does not exist with given Id: " + coachId));
        return coachMapper.mapToCoachDto(coach);
    }

    @Override
    public List<CoachDto> getAllCoaches() {
        List<Coach> coaches = coachRepository.findAll();
        return coaches.stream().map((coach) -> coachMapper.mapToCoachDto(coach))
                .collect(Collectors.toList());
    }

    @Override
    public CoachDto getCoachBySport(String sport) {
        Coach coach = coachRepository.findBySport(sport)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Coach does not exist with given sport: " + sport));
        return coachMapper.mapToCoachDto(coach);
    }

    @Override
    public CoachDto updateCoach(Long coachId, CoachDto updatedCoach) {
        Coach coach = coachRepository.findById(coachId).orElseThrow(
                () -> new ResourceNotFoundException("Coach does not exist with the given Id: " + coachId)
        );
        if (updatedCoach.getCoachFirstName() != null) {
            coach.setCoachFirstName(updatedCoach.getCoachFirstName());
        }
        if (updatedCoach.getCoachLastName() != null) {
            coach.setCoachLastName(updatedCoach.getCoachLastName());
        }
        if (updatedCoach.getSport() != null) {
            coach.setSport(updatedCoach.getSport());
        }
        if (updatedCoach.getDescription() != null) {
            coach.setDescription(updatedCoach.getDescription());
        }
        if (updatedCoach.getGender() != null) {
            coach.setGender(updatedCoach.getGender());
        }
        if (updatedCoach.getAwards() != null) {
            coach.setAwards(updatedCoach.getAwards());
        }
        if (updatedCoach.getFees() != null) {
            coach.setFees(updatedCoach.getFees());
        }
        if (updatedCoach.getDuration() != null) {
            coach.setDuration(updatedCoach.getDuration());
        }
        if (updatedCoach.getEmail() != null) {
            coach.setEmail(updatedCoach.getEmail());
        }
        if (updatedCoach.getPassword() != null) {
            coach.setPassword(updatedCoach.getPassword());
        }
        if (updatedCoach.getCoachImage() != null) {
            coach.setCoachImage(updatedCoach.getCoachImage());
        }

        Coach updatedCoachObj = coachRepository.save(coach);

        return coachMapper.mapToCoachDto(updatedCoachObj);
    }

    @Override
    public void deleteCoach(Long coachId) {
        Coach coach = coachRepository.findById(coachId).orElseThrow(
                () -> new ResourceNotFoundException("User does not exists with the given Id: " + coachId)
        );

        coachRepository.delete(coach);
    }

    @Override
    public CoachLoginResponse loginCoach(CoachLoginDto coachLoginDto) {
        Coach coach1 = coachRepository.findByEmail(coachLoginDto.getEmail());
        if (coach1 != null) {
            String password = coachLoginDto.getPassword();
            String encodedPassword = coach1.getPassword();
            boolean isPwdRight = coachPasswordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<Coach> coach = coachRepository.findOneByEmailAndPassword(coachLoginDto.getEmail(), encodedPassword);
                if (coach.isPresent()) {
                    return new CoachLoginResponse("Logged in Successfully!!", true);
                } else {
                    return new CoachLoginResponse("Login Failed", false);
                }
            } else {
                return new CoachLoginResponse("Password does not matched", false);
            }
        } else {
            return new CoachLoginResponse("Email does not exist", false);
        }
    }
}
