package com.example.Perfume.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class SurveyUser {
    @Id
    @GeneratedValue
    private Long id;

    private String gender;

    // 향기의 노트 18가지! 유저가 선택한 노트
    private int citrus;
    private int fruity;
    private int floral;
    private int aromatic;
    private int smoky;
    private int spicy;
    private int cotton;
    private int whiteMusk;
    private int aquatic;
    private int amber;
    private int green;
    private int incense;
    private int oriental;
    private int earthy;
    private int herbal;
    private int powdery;
    private int vanilla;
    private int woody;

}
