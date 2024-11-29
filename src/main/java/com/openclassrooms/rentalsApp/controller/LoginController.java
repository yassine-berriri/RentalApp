package com.openclassrooms.rentalsApp.controller;

import com.openclassrooms.rentalsApp.dtos.RegisterRequest;
import com.openclassrooms.rentalsApp.models.User;
import com.openclassrooms.rentalsApp.services.JWTService;
import com.openclassrooms.rentalsApp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class LoginController {
    private JWTService jwtService;

    private UserService userService;

    private UserDetailsService userDetailsService;

    private Authentication authentication;


    public LoginController(JWTService jwtService, UserService userService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public String getToken(Authentication authentication) {
        String token = jwtService.generateToken(authentication, "");
        return token;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody RegisterRequest registerRequest, Authentication authentication) {
        // Vérifier si l'utilisateur existe déjà
        if (userService.saveUser(registerRequest) == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "User already exists"));
        }

        String token = jwtService.generateToken(authentication, registerRequest.getEmail());

        // Retourner le token JWT dans la réponse
        return ResponseEntity.ok(Map.of("token", token));
    }
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser() {
        // Récupérer l'objet Authentication depuis le SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            return ResponseEntity.status(401).build(); // Retourner une erreur 401 si non authentifié
        }

        // Extraire les détails de l'utilisateur
        Jwt jwt = (Jwt) authentication.getPrincipal();

        String email = jwt.getClaim("email");
        // Récupérer l'utilisateur à partir du service
        User user = userService.getUserByEmail(email);

        if (user == null) {
            return ResponseEntity.status(404).build(); // Retourner une erreur 404 si l'utilisateur n'est pas trouvé
        }

        return ResponseEntity.ok(user);
    }
}
