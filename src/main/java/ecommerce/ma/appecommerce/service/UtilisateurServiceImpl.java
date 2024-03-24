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

import java.util.*;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository userRepository;
    @Autowired
    private UtilisateurMapper userMapper;

    @Override
    public List<UtilisateurResponseDTO> fetchAll() {
        List<Utilisateur> users = userRepository.findAll();
        List<UtilisateurResponseDTO> usersResDTO = new ArrayList<>();

        for (Utilisateur user: users)
            if (user != null)
                usersResDTO.add(
                        userMapper.fromUserToRes(user)
                );

        return usersResDTO;
    }

    @Override
    public UtilisateurResponseDTO searcheByID(Long id) throws NotFoundException {
        Optional<Utilisateur> user = userRepository.findById(id);
        if(user.isEmpty())
            throw new NotFoundException("Utilisateur est non-trouvé par l'identifiant "+id);
        return userMapper.fromUserToRes(user.get());
    }

    @Override
    public UtilisateurResponseDTO add(UtilisateurRequestDTO newUser) throws RequiredDataException, NotValidDataException {

        // validation l'existance de données entrées
        if (newUser.getNom().isEmpty() || newUser.getPrenom().isEmpty() || newUser.getEmail().isEmpty() || newUser.getPassword().isEmpty() )
            throw new RequiredDataException("Données maquantes dans le création d'utilisatreur !!");

        // validation le nom d'utilisateur et mot de passe
        Utilisateur exitingUsername = userRepository.findByUsername(newUser.getUsername());
        Utilisateur exitingUserPassword = userRepository.findByPassword(newUser.getPassword());
        if (exitingUsername != null || exitingUserPassword != null)
            throw new NotValidDataException("Username et/ou mot de passe dans le création d'utilisatreur sont invalides");

        // enregistrement les données d'entrées
        return userMapper.fromUserToRes(
                userRepository.save(
                        userMapper.fromReqToUser(newUser)
                )
        ) ;
    }

    @Override
    public UtilisateurResponseDTO update(UtilisateurRequestDTO newUser, Long id) throws NotFoundException, RequiredDataException, NotValidDataException {
        Utilisateur currentUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Utilisateur est non-trouvé par l'identifiant " + id));

        // validation l'existance de données entrées
        if (newUser.getNom().isEmpty() || newUser.getPrenom().isEmpty() || newUser.getEmail().isEmpty() || newUser.getPassword().isEmpty() )
            throw new RequiredDataException("Données maquantes dans le mise à jour d'utilisatreur d'id "+id+ " !!");

        // validation l'adresse éléctronique et mot de passe
        Utilisateur exitingUsername = userRepository.findByUsername(newUser.getEmail());
        Utilisateur exitingUserPassword = userRepository.findByPassword(newUser.getPassword());
        if ((exitingUsername != null && !Objects.equals(exitingUsername.getId(), id))
                || (exitingUserPassword != null && !Objects.equals(exitingUserPassword.getId(), id)))
            throw new NotValidDataException("Username et/ou mot de passe dans le mise à jour sont invalides d'utilisatreur d'id "+id+ " !!");

        // enregistrement les données d'entrées
        currentUser.setPrenom(newUser.getPrenom());
        currentUser.setNom(newUser.getNom());
        currentUser.setUsername(newUser.getUsername());
        currentUser.setAdresse(newUser.getAdresse());
        currentUser.setEmail(newUser.getEmail());
        currentUser.setPassword(newUser.getPassword());
        currentUser.setRole(newUser.getRole());

        return userMapper.fromUserToRes(
                userRepository.save(currentUser)
        );
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Optional<Utilisateur> userSearched = userRepository.findById(id);
        if(userSearched.isEmpty())
            throw new NotFoundException("Utilisateur est non-trouvé par l'identifiant "+id);

        userRepository.delete(userSearched.get());
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws UserNotFoundException {

        Utilisateur user =
                userRepository.findByUsernameAndPassword(
                        loginRequestDTO.getUsername(), loginRequestDTO.getPassword()
                );

        if (user == null)
            throw new UserNotFoundException();

        return LoginResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    @Override
    public UtilisateurResponseDTO register(UtilisateurRequestDTO utilisateurRequestDTO) throws UserCreationException {
        Utilisateur exitingUsername = userRepository.findByUsername(utilisateurRequestDTO.getEmail());
        Utilisateur exitingUserPassword = userRepository.findByPassword(utilisateurRequestDTO.getPassword());
        if (exitingUsername != null || exitingUserPassword != null)
            throw new UserCreationException();
        else {
             Utilisateur utilisateur = userMapper.fromReqToUser(utilisateurRequestDTO);
             utilisateur.setDateCreation(new Date());
             utilisateur.setRole(Role.CUSTOMER);
            return userMapper.fromUserToRes(
                    userRepository.save(utilisateur)
            );
        }
    }
}

