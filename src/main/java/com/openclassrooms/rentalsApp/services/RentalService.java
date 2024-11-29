package com.openclassrooms.rentalsApp.services;

import com.openclassrooms.rentalsApp.dtos.RentalRequest;
import com.openclassrooms.rentalsApp.models.Rental;
import com.openclassrooms.rentalsApp.models.User;
import com.openclassrooms.rentalsApp.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.UUID;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserService userService;

    public Rental saveRental(RentalRequest rental) throws IOException {
        Rental rentalToSave = new Rental();
        rentalToSave.setName(rental.getName());
        rentalToSave.setPrice(rental.getPrice());
        rentalToSave.setDescription(rental.getDescription());
        rentalToSave.setSurface(rental.getSurface());

        String picturePath = null;
        if (rental.getPicture() != null && !rental.getPicture().isEmpty()) {
            String fileName = UUID.randomUUID().toString() + "_" + rental.getPicture().getOriginalFilename();
            Path filePath = Paths.get("uploads/", fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, rental.getPicture().getBytes());
            picturePath = filePath.toString(); // Chemin du fichier local
        }

        rentalToSave.setPicture(picturePath);
        rentalToSave.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        // Récupérer l'objet Authentication depuis le SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            return null; // Retourner une erreur 401 si non authentifié
        }

        // Extraire les détails de l'utilisateur
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String email = jwt.getClaim("email");
        // Récupérer l'utilisateur à partir du service
        User user = userService.getUserByEmail(email);

        rentalToSave.setOwner(user);



        return rentalRepository.save(rentalToSave);
    }

}
