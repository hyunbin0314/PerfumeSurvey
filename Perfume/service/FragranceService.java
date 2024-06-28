package com.example.Perfume.service;

import com.example.Perfume.util.ScoreUtil;
import com.example.Perfume.entity.Perfume;
import com.example.Perfume.entity.SurveyUser;
import com.example.Perfume.repository.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class FragranceService {

    @Autowired
    private PerfumeRepository perfumeRepository;

    private int calculateMatchScore(Perfume fragrance, SurveyUser user) {
        int matchScore = 0;

        if (ScoreUtil.isMatch(fragrance.getCitrus(), user.getCitrus())) matchScore++;
        if (ScoreUtil.isMatch(fragrance.getFruity(), user.getFruity())) matchScore++;
        if (ScoreUtil.isMatch(fragrance.getFloral(), user.getFloral())) matchScore++;
        if (ScoreUtil.isMatch(fragrance.getAromatic(), user.getAromatic())) matchScore++;
        if (ScoreUtil.isMatch(fragrance.getSmoky(), user.getSmoky())) matchScore++;
        if (ScoreUtil.isMatch(fragrance.getSpicy(), user.getSpicy())) matchScore++;
        if (ScoreUtil.isMatch(fragrance.getCotton(), user.getCotton())) matchScore++;
        if (ScoreUtil.isMatch(fragrance.getWhiteMusk(), user.getWhiteMusk())) matchScore++;
        if (ScoreUtil.isMatch(fragrance.getAquatic(), user.getAquatic())) matchScore++;
        if (ScoreUtil.isMatch(fragrance.getAmber(), user.getAmber())) matchScore++;
        if (ScoreUtil.isMatch(fragrance.getGreen(), user.getGreen())) matchScore++;
        if (ScoreUtil.isMatch(fragrance.getIncense(), user.getIncense())) matchScore++;
        if (ScoreUtil.isMatch(fragrance.getOriental(), user.getOriental())) matchScore++;
        if (ScoreUtil.isMatch(fragrance.getEarthy(), user.getEarthy())) matchScore++;
        if (ScoreUtil.isMatch(fragrance.getHerbal(), user.getHerbal())) matchScore++;
        if (ScoreUtil.isMatch(fragrance.getPowdery(), user.getPowdery())) matchScore++;
        if (ScoreUtil.isMatch(fragrance.getVanilla(), user.getVanilla())) matchScore++;
        if (ScoreUtil.isMatch(fragrance.getWoody(), user.getWoody())) matchScore++;

        return matchScore;
    }

    private int calculateTotalDifference(Perfume fragrance, SurveyUser user) {
        int totalDifference = 0;

        totalDifference += Math.abs(fragrance.getCitrus() - user.getCitrus());
        totalDifference += Math.abs(fragrance.getFruity() - user.getFruity());
        totalDifference += Math.abs(fragrance.getFloral() - user.getFloral());
        totalDifference += Math.abs(fragrance.getAromatic() - user.getAromatic());
        totalDifference += Math.abs(fragrance.getSmoky() - user.getSmoky());
        totalDifference += Math.abs(fragrance.getSpicy() - user.getSpicy());
        totalDifference += Math.abs(fragrance.getCotton() - user.getCotton());
        totalDifference += Math.abs(fragrance.getWhiteMusk() - user.getWhiteMusk());
        totalDifference += Math.abs(fragrance.getAquatic() - user.getAquatic());
        totalDifference += Math.abs(fragrance.getAmber() - user.getAmber());
        totalDifference += Math.abs(fragrance.getGreen() - user.getGreen());
        totalDifference += Math.abs(fragrance.getIncense() - user.getIncense());
        totalDifference += Math.abs(fragrance.getOriental() - user.getOriental());
        totalDifference += Math.abs(fragrance.getEarthy() - user.getEarthy());
        totalDifference += Math.abs(fragrance.getHerbal() - user.getHerbal());
        totalDifference += Math.abs(fragrance.getPowdery() - user.getPowdery());
        totalDifference += Math.abs(fragrance.getVanilla() - user.getVanilla());
        totalDifference += Math.abs(fragrance.getWoody() - user.getWoody());

        return totalDifference;
    }

    public List<Perfume> getAllFragrances() {
        return perfumeRepository.findAll();
    }

    public Perfume findBestMatchingFragrance(SurveyUser user) {
        List<Perfume> fragrances = getAllFragrances();
        Perfume bestMatch = null;
        int highestMatchScore = -1;
        int lowestDifference = Integer.MAX_VALUE;

        for (Perfume fragrance : fragrances) {
            int matchScore = calculateMatchScore(fragrance, user);
            int totalDifference = calculateTotalDifference(fragrance, user);

            if (matchScore > highestMatchScore || (matchScore == highestMatchScore && totalDifference < lowestDifference)) {
                highestMatchScore = matchScore;
                lowestDifference = totalDifference;
                bestMatch = fragrance;
            }
        }

        return bestMatch;
    }
}


