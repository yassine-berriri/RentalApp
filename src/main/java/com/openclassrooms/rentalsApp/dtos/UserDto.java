package com.openclassrooms.rentalsApp.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    Long id;
    String name;
    String email;
    LocalDate created_at;
    LocalDate updated_at;
}
