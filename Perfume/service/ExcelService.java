package com.example.Perfume.service;

import com.example.Perfume.entity.Perfume;
import com.example.Perfume.repository.PerfumeRepository;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;

@Service

public class ExcelService {
    @Autowired
    private PerfumeRepository perfumeRepository;

    public void saveExcelData(MultipartFile file) {
        try (InputStream is = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    // Skip header row
                    continue;
                }

                String perfumeName = row.getCell(0).getStringCellValue();
                String imageUrl = row.getCell(1).getStringCellValue();
                String brand = row.getCell(2).getStringCellValue();

                // Check if the perfume already exists
                if (!perfumeRepository.existsByPerfumeNameAndBrand(perfumeName, brand)) {
                    Perfume perfume = new Perfume();
                    perfume.setPerfumeName(perfumeName);
                    perfume.setImageUrl(imageUrl);
                    perfume.setBrand(brand);
                    perfume.setCitrus(getIntCellValue(row, 3));
                    perfume.setFruity(getIntCellValue(row, 4));
                    perfume.setFloral(getIntCellValue(row, 5));
                    perfume.setAromatic(getIntCellValue(row, 6));
                    perfume.setSmoky(getIntCellValue(row, 7));
                    perfume.setSpicy(getIntCellValue(row, 8));
                    perfume.setCotton(getIntCellValue(row, 9));
                    perfume.setWhiteMusk(getIntCellValue(row, 10));
                    perfume.setAquatic(getIntCellValue(row, 11));
                    perfume.setAmber(getIntCellValue(row, 12));
                    perfume.setGreen(getIntCellValue(row, 13));
                    perfume.setIncense(getIntCellValue(row, 14));
                    perfume.setOriental(getIntCellValue(row, 15));
                    perfume.setEarthy(getIntCellValue(row, 16));
                    perfume.setHerbal(getIntCellValue(row, 17));
                    perfume.setPowdery(getIntCellValue(row, 18));
                    perfume.setVanilla(getIntCellValue(row, 19));
                    perfume.setWoody(getIntCellValue(row, 20));

                    perfumeRepository.save(perfume);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Integer getIntCellValue(Row row, int cellIndex) {
        if (row.getCell(cellIndex) == null) {
            return 0; // 빈 셀인 경우 0 반환
        }
        CellType cellType = row.getCell(cellIndex).getCellType();
        switch (cellType) {
            case NUMERIC:
                return (int) row.getCell(cellIndex).getNumericCellValue();
            case STRING:
                try {
                    return Integer.parseInt(row.getCell(cellIndex).getStringCellValue());
                } catch (NumberFormatException e) {
                    return 0; // 문자열이 숫자로 변환할 수 없는 경우 0 반환
                }
            default:
                return 0; // 다른 타입의 경우 0 반환
        }
    }
}
