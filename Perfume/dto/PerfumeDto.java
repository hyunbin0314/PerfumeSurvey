package com.example.Perfume.dto;

import com.example.Perfume.entity.Perfume;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter

public class PerfumeDto {

    private Connection connection;

    public PerfumeDto(Connection connection) {
        this.connection = connection;
    }

    public List<Perfume> getAllFragrances() {
        List<Perfume> fragrances = new ArrayList<>();
        String query = "SELECT * FROM perfume";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Perfume fragrance = new Perfume();
                fragrance.setId(resultSet.getLong("id"));
                fragrance.setPerfumeName(resultSet.getString("perfume_name"));
                fragrance.setImageUrl(resultSet.getString("image_url"));
                fragrance.setBrand(resultSet.getString("brand"));
                fragrance.setCitrus(resultSet.getInt("citrus"));
                fragrance.setFruity(resultSet.getInt("fruity"));
                fragrance.setFloral(resultSet.getInt("floral"));
                fragrance.setAromatic(resultSet.getInt("aromatic"));
                fragrance.setSmoky(resultSet.getInt("smoky"));
                fragrance.setSpicy(resultSet.getInt("spicy"));
                fragrance.setCotton(resultSet.getInt("cotton"));
                fragrance.setWhiteMusk(resultSet.getInt("white_musk"));
                fragrance.setAquatic(resultSet.getInt("aquatic"));
                fragrance.setAmber(resultSet.getInt("amber"));
                fragrance.setGreen(resultSet.getInt("green"));
                fragrance.setIncense(resultSet.getInt("incense"));
                fragrance.setOriental(resultSet.getInt("oriental"));
                fragrance.setEarthy(resultSet.getInt("earthy"));
                fragrance.setHerbal(resultSet.getInt("herbal"));
                fragrance.setPowdery(resultSet.getInt("powdery"));
                fragrance.setVanilla(resultSet.getInt("vanilla"));
                fragrance.setWoody(resultSet.getInt("woody"));
                fragrances.add(fragrance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fragrances;
    }

}
