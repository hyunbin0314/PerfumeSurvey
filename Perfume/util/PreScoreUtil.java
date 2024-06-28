package com.example.Perfume.util;

public class PreScoreUtil {

    public static int adjustScore(double score) {
        return (int) Math.ceil(score);
    }

    public static boolean isMatch(double fragranceScore, double userScore) {
        return adjustScore(fragranceScore) == adjustScore(userScore);
    }

}
