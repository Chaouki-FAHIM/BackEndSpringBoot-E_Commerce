package ecommerce.ma.appecommerce.service;

import ecommerce.ma.appecommerce.DTO.request.LoginRequestDTO;
import ecommerce.ma.appecommerce.DTO.request.UtilisateurRequestDTO;
import ecommerce.ma.appecommerce.DTO.response.LoginResponseDTO;
import ecommerce.ma.appecommerce.DTO.response.UtilisateurResponseDTO;
import ecommerce.ma.appecommerce.mapper.UtilisateurMapper;
import ecommerce.ma.appecommerce.model.Role;
import ecommerce.ma.appecommerce.model.entity.Utilisateur;
import ecommerce.ma.appecommerce.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private UtilisateurMapper utilisateurMapper;

    @Override
    public List<UtilisateurResponseDTO> fetchAll() {
        return null;
    }

    @Override
    public UtilisateurResponseDTO searcheByID(Long aLong) {
        return null;
    }

    @Override
    public UtilisateurResponseDTO add(UtilisateurRequestDTO utilisateurRequestDTO) {
        return null;
    }

    @Override
    public UtilisateurResponseDTO update(UtilisateurRequestDTO utilisateurRequestDTO, Long aLong) {
        return null;
    }

    @Override
    public UtilisateurResponseDTO delete(Long aLong) {
        return null;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        Utilisateur user =
                utilisateurRepository.findByEmailAndPasswd(
                        loginRequestDTO.getEmail(), loginRequestDTO.getPasswd()
                );

        if (user != null)
            return LoginResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .passwd(user.getPasswd())
                .role(user.getRole())
                .build();
        return null;
    }

    @Override
    public UtilisateurResponseDTO register(UtilisateurRequestDTO utilisateurRequestDTO) {
        Utilisateur exitingUserEmail = utilisateurRepository.findByEmail(utilisateurRequestDTO.getEmail());
        Utilisateur exitingUserPassword = utilisateurRepository.findByEmail(utilisateurRequestDTO.getPasswd());
        if (exitingUserEmail != null || exitingUserPassword != null)
            return null;
        else {
             Utilisateur utilisateur = utilisateurMapper.fromUserReqDTO(utilisateurRequestDTO);
             utilisateur.setDateCreation(new Date());
             utilisateur.setRole(Role.CUSTOMER);
            return utilisateurMapper.fromUser(utilisateurRepository.save(utilisateur));
        }
    }
}

