package com.openclassrooms.rentalsApp.controller;

import com.openclassrooms.rentalsApp.dtos.RentalRequest;
import com.openclassrooms.rentalsApp.models.Rental;
import com.openclassrooms.rentalsApp.services.RentalService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;


@RestController
@RequestMapping("api/rentals")
public class RentalController {

    RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> createRental(    @RequestParam("name") String name,
                                                                @RequestParam("surface") Double surface,
                                                                @RequestParam("price") Double price,
                                                                @RequestParam("description") String description,
                                                                @RequestParam("picture") MultipartFile picture) throws IOException {
       /*
        if (rentalService.saveRental(rental)==null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Rental already exists"));
        }
        else {
            rentalService.saveRental(rental);
            return ResponseEntity.ok(Map.of("message", "Rental created!"));
        }

        */
        RentalRequest rental = new RentalRequest();
        rental.setName(name);
        rental.setSurface(surface);
        rental.setPrice(price);
        rental.setDescription(description);
        rental.setPicture(picture);

        if (rentalService.saveRental(rental)==null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Rental already exists"));
        }
        else {

            return ResponseEntity.ok(Map.of("message", "Rental created!"));
        }

    }
}
