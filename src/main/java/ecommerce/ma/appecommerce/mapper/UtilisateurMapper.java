package ecommerce.ma.appecommerce.mapper;

import ecommerce.ma.appecommerce.DTO.request.UtilisateurRequestDTO;
import ecommerce.ma.appecommerce.DTO.response.UtilisateurResponseDTO;
import ecommerce.ma.appecommerce.model.entity.Utilisateur;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UtilisateurMapper {

    public UtilisateurResponseDTO fromUserToRes(Utilisateur utilisateur) {
        UtilisateurResponseDTO utilisateurResponseDTO = new UtilisateurResponseDTO();
        BeanUtils.copyProperties(utilisateur, utilisateurResponseDTO);
        return utilisateurResponseDTO;
    }

    public Utilisateur fromReqToUser(UtilisateurRequestDTO utilisateurRequestDTO) {

        return Utilisateur.builder()
                .nom(utilisateurRequestDTO.getNom())
                .prenom(utilisateurRequestDTO.getPrenom())
                .username(utilisateurRequestDTO.getUsername())
                .adresse(utilisateurRequestDTO.getAdresse())
                .role(utilisateurRequestDTO.getRole())
                .email(utilisateurRequestDTO.getEmail())
                .password(utilisateurRequestDTO.getPassword())
                .build();
    }

    public UtilisateurRequestDTO fromUserToReq(Optional<Utilisateur> utilisateur) {
        UtilisateurRequestDTO userReqDTO = new UtilisateurRequestDTO();
        BeanUtils.copyProperties(utilisateur,userReqDTO);
        return userReqDTO;
    }
}
