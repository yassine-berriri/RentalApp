package com.openclassrooms.rentalsApp.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RentalRequestTest {
    private String name;
    private Double surface;
    private Double price;
    private String description;
}