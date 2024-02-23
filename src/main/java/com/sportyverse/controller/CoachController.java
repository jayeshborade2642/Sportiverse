package com.sportyverse.controller;

import com.sportyverse.dto.CoachDto;
import com.sportyverse.dto.CoachLoginDto;
import com.sportyverse.response.CoachLoginResponse;
import com.sportyverse.service.CoachService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/coaches")
public class CoachController {

    private CoachService coachService;

    //Add Coach RestAPI
    @PostMapping(path = "/create")
    public ResponseEntity<CoachDto> createCoach(@RequestBody CoachDto coachDto){
        CoachDto savedCoach = coachService.createCoach(coachDto);
        return new ResponseEntity<>(savedCoach, HttpStatus.CREATED);
    }

    //Add Coach RestAPI
    @GetMapping("{cid}")
    public ResponseEntity<CoachDto> getCoachById(@PathVariable("cid") Long coachId){
        CoachDto coachDto = coachService.getCoachById(coachId);
        return ResponseEntity.ok(coachDto);
    }

    //Get All Coaches RestAPI
    @GetMapping
    public ResponseEntity<List<CoachDto>> getAllCoaches(){
        List<CoachDto> coaches = coachService.getAllCoaches();
        return ResponseEntity.ok(coaches);
    }

    //Get Coach by Sport RestAPI
    @GetMapping("/bysport/{sport}")
    public ResponseEntity<CoachDto> getCoachBySport(@PathVariable("sport") String sport){
        CoachDto coachDto = coachService.getCoachBySport(sport);
        return ResponseEntity.ok(coachDto);
    }

    //Update Coach RestAPI
    @PutMapping("{cid}")
    public ResponseEntity<CoachDto> updateCoach(@PathVariable("cid") Long coachId,
                                                @RequestBody CoachDto updatedCoach){
        CoachDto coachDto = coachService.updateCoach(coachId, updatedCoach);
        return ResponseEntity.ok(coachDto);
    }

    @DeleteMapping("/{cid}")
    public ResponseEntity<String> deleteCoach(@PathVariable("cid") Long coachId){
        coachService.deleteCoach(coachId);
        return ResponseEntity.ok("Coach deleted successfully..!!");
    }

    //Login Coach RestAPI
    @PostMapping(path = "/login")
    public ResponseEntity<?> loginCoach(@RequestBody CoachLoginDto coachLoginDto){
        CoachLoginResponse coachLoginResponse = coachService.loginCoach(coachLoginDto);
        return ResponseEntity.ok(coachLoginResponse);
    }
}
