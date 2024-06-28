package com.example.Perfume.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Perfume {

    @Id
    @GeneratedValue
    private Long id;

    private String perfumeName;
    private String imageUrl;
    private String brand;

    // 향기의 노트 18가지!
    // 노트는 추가되지않은 정적자원으로 명확성을 높이기위해 필드를 사용
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
