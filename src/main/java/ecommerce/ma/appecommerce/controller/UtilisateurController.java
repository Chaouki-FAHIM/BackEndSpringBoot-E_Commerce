    package ecommerce.ma.appecommerce.controller;

    import ecommerce.ma.appecommerce.DTO.request.LoginRequestDTO;
    import ecommerce.ma.appecommerce.DTO.request.UtilisateurRequestDTO;
    import ecommerce.ma.appecommerce.DTO.response.LoginResponseDTO;
    import ecommerce.ma.appecommerce.DTO.response.UtilisateurResponseDTO;
    import ecommerce.ma.appecommerce.exception.*;
    import ecommerce.ma.appecommerce.service.UtilisateurService;
    import org.springframework.beans.factory.annotation.Autowired;

    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;

    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @CrossOrigin("http://localhost:3000")
    @RestController
    public class UtilisateurController {

        @Autowired
        private UtilisateurService userService;


        @GetMapping("/utilisateurs")
        @PreAuthorize("hasAuthority('SCOPE_USER')")
        ResponseEntity<?> showAllUsers() {
            try {
                List<UtilisateurResponseDTO> usersResDTO = userService.fetchAll();
                return ResponseEntity.ok(
                        usersResDTO
                );
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }

        @GetMapping("/utilisateurs/{id}")
        @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
        ResponseEntity<?> getUserById(@PathVariable Long id) throws NotFoundException {
            try {
                UtilisateurResponseDTO userResDTO = userService.searcheByID(id);
                return ResponseEntity.ok(
                        userResDTO
                );
            } catch (NotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }

        @PostMapping("/utilisateurs")
        @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
        ResponseEntity<?> newUtilisateur(@RequestBody UtilisateurRequestDTO newUserReqDTO){

            try {
                UtilisateurResponseDTO userResDTO = userService.add(newUserReqDTO);
                return ResponseEntity.ok(userResDTO);
            } catch (RequiredDataException | NotValidDataException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }

        @PutMapping("/utilisateurs/{id}")
        @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
        ResponseEntity<?> updateUser(@RequestBody UtilisateurRequestDTO newUserReqDTO, @PathVariable Long id) throws RequiredDataException, NotValidDataException, NotFoundException {
            try {
                UtilisateurResponseDTO userResDTO = userService.update(newUserReqDTO,id);
                return ResponseEntity.ok(userResDTO);
            } catch (NotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur est non-trouvé par l'identifiant "+id);
            } catch (RequiredDataException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Données maquantes dans le mise à jour d'utilisatreur");
            } catch (NotValidDataException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email et/ou mot de passe sont invalides d'utilisatreur");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }

        @DeleteMapping("/utilisateurs/{id}")
        @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
        ResponseEntity<String> deleteUser(@PathVariable Long id){
            try {
                userService.delete(id);
                return ResponseEntity.ok("Suppression d'utilisateur avec succès");
            } catch (NotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur est non-trouvé par l'identifiant "+id);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }

        @PostMapping("/login")
        ResponseEntity<?> login(@RequestBody LoginRequestDTO loginDTO) throws UserNotFoundException {
            LoginResponseDTO loginResDTO = userService.login(loginDTO);
            if (loginResDTO == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email et/ou mot de passe dans l'inscription sont invalides");
            return ResponseEntity.ok(
                    loginResDTO
            );
        }

        @PostMapping("/register")
        ResponseEntity<?> register(@RequestBody UtilisateurRequestDTO utilisateurRequestDTO) throws UserCreationException {
            UtilisateurResponseDTO userResDTO = userService.register(utilisateurRequestDTO);
            if (userResDTO == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email et/ou mot de passe dans l'inscription sont invalides");
            return ResponseEntity.ok(
                    userResDTO
            );
        }
    }
