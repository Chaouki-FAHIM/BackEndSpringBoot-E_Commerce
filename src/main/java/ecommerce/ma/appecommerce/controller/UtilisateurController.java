    package ecommerce.ma.appecommerce.controller;

    import ecommerce.ma.appecommerce.DTO.request.LoginRequestDTO;
    import ecommerce.ma.appecommerce.DTO.request.UtilisateurRequestDTO;
    import ecommerce.ma.appecommerce.DTO.response.LoginResponseDTO;
    import ecommerce.ma.appecommerce.model.entity.Utilisateur;
    import ecommerce.ma.appecommerce.repository.UtilisateurRepository;
    import ecommerce.ma.appecommerce.service.UtilisateurService;
    import org.springframework.beans.factory.annotation.Autowired;

    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    @CrossOrigin("http://localhost:3000")
    @RestController
    public class UtilisateurController {

        @Autowired
        private UtilisateurService userService;

        @Autowired
        private UtilisateurRepository utilisateurRepository;

        @PostMapping("/utilisateur")
        Utilisateur newUtilisateur(@RequestBody Utilisateur newUser){
            return utilisateurRepository.save(newUser);
        }

        @GetMapping("/utilisateurs")
        List<Utilisateur> showAllutilisateurs(){
            return utilisateurRepository.findAll();
        }


        @GetMapping("/utilisateur/{id}")
        Utilisateur getUtilisateurById(@PathVariable Long id) {
            return utilisateurRepository.findById(id).orElse(null);
        }

        @PutMapping("/utilisateur/{id}")
        Utilisateur updateUtilisateur(@RequestBody Utilisateur newUtilisateur, @PathVariable Long id) {
            return utilisateurRepository.findById(id)
                    .map(user -> {
                        user.setPrenom(newUtilisateur.getPrenom());
                        user.setNom(newUtilisateur.getNom());
                        user.setPasswd(newUtilisateur.getPasswd());
                        user.setEmail(newUtilisateur.getEmail());
                        user.setAdress(newUtilisateur.getAdress());
                        user.setRole(newUtilisateur.getRole());

                        return utilisateurRepository.save(user);
                    })
                    .orElseGet(() -> {
                        newUtilisateur.setId(id);
                        return utilisateurRepository.save(newUtilisateur);
                    });
        }

        @DeleteMapping("/utilisateur/{id}")
        String deleteUtilisateur(@PathVariable Long id){
            utilisateurRepository.deleteById(id);
            return "Suppression avec succès pour cet utilisateur : " + id;
        }

        @PostMapping("/login")
        LoginResponseDTO login(@RequestBody LoginRequestDTO loginDTO) {
            return userService.login(loginDTO);
        }

        @PostMapping("/register")
        ResponseEntity<?> register(@RequestBody UtilisateurRequestDTO utilisateurRequestDTO) {
            Object object = userService.register(utilisateurRequestDTO);
            if (object == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'email ou mot de passe invalide");
            return ResponseEntity.ok(
                    object
            );
        }
    }
