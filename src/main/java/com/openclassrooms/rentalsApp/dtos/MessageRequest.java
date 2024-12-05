package com.openclassrooms.rentalsApp.dtos;

import lombok.Data;

@Data
public class MessageRequest {
    private Long rental_id;
    private Long user_id;
    private String message;
}
