package com.example.Perfume.repository;

import com.example.Perfume.entity.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfumeRepository extends JpaRepository<Perfume, Long> {
    boolean existsByPerfumeNameAndBrand(String perfumeName, String brand);

}
