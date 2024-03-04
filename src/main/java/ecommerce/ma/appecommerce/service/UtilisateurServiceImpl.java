package ecommerce.ma.appecommerce.service;

import ecommerce.ma.appecommerce.DTO.request.LoginRequestDTO;
import ecommerce.ma.appecommerce.DTO.request.UtilisateurRequestDTO;
import ecommerce.ma.appecommerce.DTO.response.LoginResponseDTO;
import ecommerce.ma.appecommerce.DTO.response.UtilisateurResponseDTO;
import ecommerce.ma.appecommerce.exception.*;
import ecommerce.ma.appecommerce.mapper.UtilisateurMapper;
import ecommerce.ma.appecommerce.model.Role;
import ecommerce.ma.appecommerce.model.entity.Utilisateur;
import ecommerce.ma.appecommerce.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository userRepository;
    @Autowired
    private UtilisateurMapper userMapper;

    @Override
    public List<UtilisateurResponseDTO> fetchAll() {
        List<Utilisateur> users = userRepository.findAll();
        List<UtilisateurResponseDTO> usersResDTO = null;

        for (Utilisateur user: users)
            usersResDTO.add(
                    userMapper.fromUserToRes(Optional.ofNullable(user))
            );
        return usersResDTO;
    }

    @Override
    public UtilisateurResponseDTO searcheByID(Long id) throws NotFoundException {
        Optional<Utilisateur> user = userRepository.findById(id);
        if(user.isPresent())
            throw new NotFoundException("Utilisateur est non-trouvé par l'identifiant "+id);
        return userMapper.fromUserToRes(user);
    }

    @Override
    public UtilisateurResponseDTO add(UtilisateurRequestDTO newUser) throws RequiredDataException, NotValidDataException {

        // validation l'existance de données entrées
        if (newUser.getNom().isEmpty() || newUser.getPrenom().isEmpty() || newUser.getEmail().isEmpty() || newUser.getPasswd().isEmpty() )
            throw new RequiredDataException("Données maquantes dans le création d'utilisatreur !!");

        // validation l'adresse éléctronique et mot de passe
        Utilisateur exitingUserEmail = userRepository.findByEmail(newUser.getEmail());
        Utilisateur exitingUserPassword = userRepository.findByPasswd(newUser.getPasswd());
        if (exitingUserEmail != null || exitingUserPassword != null)
            throw new NotValidDataException("Email et/ou mot de passe dans le création sont invalides d'utilisatreur");

        // enregistrement les données d'entrées
        return userMapper.fromUserToRes(
                Optional.of(
                        userRepository.save(
                                userMapper.fromReqToUser(newUser)
                        )
                )
        ) ;
    }

    @Override
    public UtilisateurResponseDTO update(UtilisateurRequestDTO newUser, Long id) throws NotFoundException, RequiredDataException, NotValidDataException {
        Optional<Utilisateur> currentUser = userRepository.findById(id);
        if(currentUser.isEmpty())
            throw new NotFoundException("Utilisateur est non-trouvé par l'identifiant "+id);

        // validation l'existance de données entrées
        if (newUser.getNom().isEmpty() || newUser.getPrenom().isEmpty() || newUser.getEmail().isEmpty() || newUser.getPasswd().isEmpty() )
            throw new RequiredDataException("Données maquantes dans le mise à jour d'utilisatreur d'id "+id+ " !!");

        // validation l'adresse éléctronique et mot de passe
        Utilisateur exitingUserEmail = userRepository.findByEmail(newUser.getEmail());
        Utilisateur exitingUserPassword = userRepository.findByPasswd(newUser.getPasswd());
        if (exitingUserEmail != null || exitingUserPassword != null)
            throw new NotValidDataException("Email et/ou mot de passe dans le mise à jour sont invalides d'utilisatreur d'id "+id+ " !!");

        // enregistrement les données d'entrées
        UtilisateurRequestDTO userReqDTO= userMapper.fromUserToReq(currentUser);
        return userMapper.fromUserToRes(
                userRepository.findById(id)
                        .map(user -> {
                            userReqDTO.setPrenom(newUser.getPrenom());
                            userReqDTO.setNom(newUser.getNom());
                            userReqDTO.setAdresse(newUser.getAdresse());
                            userReqDTO.setEmail(newUser.getEmail());
                            userReqDTO.setPasswd(newUser.getPasswd());
                            userReqDTO.setRole(newUser.getRole());
                            return userRepository.save(userMapper.fromReqToUser(userReqDTO));
                        })
        ) ;
    }

    @Override
    public UtilisateurResponseDTO delete(Long id) throws NotFoundException {
        Optional<Utilisateur> userSearched = userRepository.findById(id);
        if(userSearched.isEmpty())
            throw new NotFoundException("Utilisateur est non-trouvé par l'identifiant "+id);

        return userMapper.fromUserToRes(
                Optional.ofNullable(userSearched.orElseThrow(() -> new IllegalStateException("La valeur n'est pas présente")))
        );
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws UserNotFoundException {

        Utilisateur user =
                userRepository.findByEmailAndPasswd(
                        loginRequestDTO.getEmail(), loginRequestDTO.getPasswd()
                );

        if (user == null)
            throw new UserNotFoundException();

        return LoginResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .passwd(user.getPasswd())
                .role(user.getRole())
                .build();
    }

    @Override
    public UtilisateurResponseDTO register(UtilisateurRequestDTO utilisateurRequestDTO) throws UserCreationException {
        Utilisateur exitingUserEmail = userRepository.findByEmail(utilisateurRequestDTO.getEmail());
        Utilisateur exitingUserPassword = userRepository.findByPasswd(utilisateurRequestDTO.getPasswd());
        if (exitingUserEmail != null || exitingUserPassword != null)
            throw new UserCreationException();
        else {
             Utilisateur utilisateur = userMapper.fromReqToUser(utilisateurRequestDTO);
             utilisateur.setDateCreation(new Date());
             utilisateur.setRole(Role.CUSTOMER);
            return userMapper.fromUserToRes(
                    Optional.of(userRepository.save(utilisateur))
            );
        }
    }
}

