package com.openclassrooms.rentalsApp.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
public class RentalRequest {
    private String name;
    private Double surface;
    private Double price;
    private MultipartFile picture;
    private String description;
}
