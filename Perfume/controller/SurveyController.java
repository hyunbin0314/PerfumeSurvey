package com.example.Perfume.controller;

import com.example.Perfume.entity.Perfume;
import com.example.Perfume.entity.SurveyUser;
import com.example.Perfume.service.PreciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Perfume.service.FragranceService;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    @Autowired
    private FragranceService fragranceService;

    @Autowired
    private PreciesService preciseService;

    @PostMapping("/simple/recommend")
    public Perfume recommendFragrance(@RequestBody SurveyUser user) {
        return fragranceService.findBestMatchingFragrance(user);
    }

    @PostMapping("/pre/recommend")
    public Perfume prerecommendFragrance(@RequestBody SurveyUser user) {
        return preciseService.findBestMatchingFragrance(user);
    }
}
