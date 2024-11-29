package com.openclassrooms.rentalsApp.repository;

import com.openclassrooms.rentalsApp.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {

}
