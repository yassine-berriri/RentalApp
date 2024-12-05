# RentalsApp Backend

Ce projet représente la partie backend de l'application **RentalsApp**, une plateforme de mise en relation entre futurs locataires et propriétaires.

## **Fonctionnalités principales**
- Gestion sécurisée des utilisateurs et des propriétés grâce à **Spring Security** et **OAuth2**.
- API REST pour les opérations principales de l'application.
- Documentation des APIs avec **Swagger**.

---

## **Lancement du projet**

### **Prérequis**
- Java 11 ou supérieur
- Maven
- Node.js et Angular CLI (version 14 ou ultérieure)

### **Étapes pour lancer le projet**

1. **Cloner le dépôt** :
   git clone https://github.com/yassine-berriri/RentalApp.git
2. **Lancer le backend** :
   mvn spring-boot:run
3. **Lancer le frontend** :
   ng serve --proxy-config src/proxy.config.json

---

## **Documentation API**

La documentation des APIs est accessible via Swagger : http://localhost:9090/swagger-ui/index.html

### **Étapes pour s'authentifier et utiliser les APIs**

1. **Créer un utilisateur** :
   - Allez dans l'endpoint POST /api/auth/register via Swagger.
   - Cliquez sur Try it out, configurez le Request Body, et cliquez sur Execute.
   - Vous recevrez un token dans la réponse.

2. **Ajouter le token** :
   - Cliquez sur Authorize dans Swagger.
   - Collez le token reçu précédemment pour accéder aux autres APIs.
