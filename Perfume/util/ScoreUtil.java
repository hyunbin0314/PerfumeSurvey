package com.example.Perfume.util;

public class ScoreUtil {

    public static boolean isMatch(int fragranceScore, int userScore) {
        switch (userScore) {
            case 2:
                return fragranceScore == 0 || fragranceScore == 1 || fragranceScore == 2;
            case 4:
                return fragranceScore == 3 || fragranceScore == 4;
            case 6:
                return fragranceScore == 5 || fragranceScore == 6;
            case 8:
                return fragranceScore == 7 || fragranceScore == 8;
            case 10:
                return fragranceScore == 9 || fragranceScore == 10;
            default:
                return false;
        }
    }


}
