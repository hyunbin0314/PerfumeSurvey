package com.example.Perfume.service;

import com.example.Perfume.entity.Perfume;
import com.example.Perfume.entity.SurveyUser;
import com.example.Perfume.repository.PerfumeRepository;
import com.example.Perfume.util.PreScoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PreciesService {
    @Autowired
    PerfumeRepository perfumeRepository;


    private int calculatePreMatchScore(Perfume fragrance, SurveyUser user) {
        int matchScore = 0;

        if (PreScoreUtil.isMatch(fragrance.getCitrus(), user.getCitrus())) matchScore++;
        if (PreScoreUtil.isMatch(fragrance.getFruity(), user.getFruity())) matchScore++;
        if (PreScoreUtil.isMatch(fragrance.getFloral(), user.getFloral())) matchScore++;
        if (PreScoreUtil.isMatch(fragrance.getAromatic(), user.getAromatic())) matchScore++;
        if (PreScoreUtil.isMatch(fragrance.getSmoky(), user.getSmoky())) matchScore++;
        if (PreScoreUtil.isMatch(fragrance.getSpicy(), user.getSpicy())) matchScore++;
        if (PreScoreUtil.isMatch(fragrance.getCotton(), user.getCotton())) matchScore++;
        if (PreScoreUtil.isMatch(fragrance.getWhiteMusk(), user.getWhiteMusk())) matchScore++;
        if (PreScoreUtil.isMatch(fragrance.getAquatic(), user.getAquatic())) matchScore++;
        if (PreScoreUtil.isMatch(fragrance.getAmber(), user.getAmber())) matchScore++;
        if (PreScoreUtil.isMatch(fragrance.getGreen(), user.getGreen())) matchScore++;
        if (PreScoreUtil.isMatch(fragrance.getIncense(), user.getIncense())) matchScore++;
        if (PreScoreUtil.isMatch(fragrance.getOriental(), user.getOriental())) matchScore++;
        if (PreScoreUtil.isMatch(fragrance.getEarthy(), user.getEarthy())) matchScore++;
        if (PreScoreUtil.isMatch(fragrance.getHerbal(), user.getHerbal())) matchScore++;
        if (PreScoreUtil.isMatch(fragrance.getPowdery(), user.getPowdery())) matchScore++;
        if (PreScoreUtil.isMatch(fragrance.getVanilla(), user.getVanilla())) matchScore++;
        if (PreScoreUtil.isMatch(fragrance.getWoody(), user.getWoody())) matchScore++;

        return matchScore;
    }

    private int calculatePreTotalDifference(Perfume fragrance, SurveyUser user) {
        int totalDifference = 0;

        totalDifference += Math.abs(PreScoreUtil.adjustScore(fragrance.getCitrus()) - PreScoreUtil.adjustScore(user.getCitrus()));
        totalDifference += Math.abs(PreScoreUtil.adjustScore(fragrance.getFruity()) - PreScoreUtil.adjustScore(user.getFruity()));
        totalDifference += Math.abs(PreScoreUtil.adjustScore(fragrance.getFloral()) - PreScoreUtil.adjustScore(user.getFloral()));
        totalDifference += Math.abs(PreScoreUtil.adjustScore(fragrance.getAromatic()) - PreScoreUtil.adjustScore(user.getAromatic()));
        totalDifference += Math.abs(PreScoreUtil.adjustScore(fragrance.getSmoky()) - PreScoreUtil.adjustScore(user.getSmoky()));
        totalDifference += Math.abs(PreScoreUtil.adjustScore(fragrance.getSpicy()) - PreScoreUtil.adjustScore(user.getSpicy()));
        totalDifference += Math.abs(PreScoreUtil.adjustScore(fragrance.getCotton()) - PreScoreUtil.adjustScore(user.getCotton()));
        totalDifference += Math.abs(PreScoreUtil.adjustScore(fragrance.getWhiteMusk()) - PreScoreUtil.adjustScore(user.getWhiteMusk()));
        totalDifference += Math.abs(PreScoreUtil.adjustScore(fragrance.getAquatic()) - PreScoreUtil.adjustScore(user.getAquatic()));
        totalDifference += Math.abs(PreScoreUtil.adjustScore(fragrance.getAmber()) - PreScoreUtil.adjustScore(user.getAmber()));
        totalDifference += Math.abs(PreScoreUtil.adjustScore(fragrance.getGreen()) - PreScoreUtil.adjustScore(user.getGreen()));
        totalDifference += Math.abs(PreScoreUtil.adjustScore(fragrance.getIncense()) - PreScoreUtil.adjustScore(user.getIncense()));
        totalDifference += Math.abs(PreScoreUtil.adjustScore(fragrance.getOriental()) - PreScoreUtil.adjustScore(user.getOriental()));
        totalDifference += Math.abs(PreScoreUtil.adjustScore(fragrance.getEarthy()) - PreScoreUtil.adjustScore(user.getEarthy()));
        totalDifference += Math.abs(PreScoreUtil.adjustScore(fragrance.getHerbal()) - PreScoreUtil.adjustScore(user.getHerbal()));
        totalDifference += Math.abs(PreScoreUtil.adjustScore(fragrance.getPowdery()) - PreScoreUtil.adjustScore(user.getPowdery()));
        totalDifference += Math.abs(PreScoreUtil.adjustScore(fragrance.getVanilla()) - PreScoreUtil.adjustScore(user.getVanilla()));
        totalDifference += Math.abs(PreScoreUtil.adjustScore(fragrance.getWoody()) - PreScoreUtil.adjustScore(user.getWoody()));

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
            int matchScore = calculatePreMatchScore(fragrance, user);
            int totalDifference = calculatePreTotalDifference(fragrance, user);

            if (matchScore > highestMatchScore || (matchScore == highestMatchScore && totalDifference < lowestDifference)) {
                highestMatchScore = matchScore;
                lowestDifference = totalDifference;
                bestMatch = fragrance;
            }
        }

        return bestMatch;
    }
}

