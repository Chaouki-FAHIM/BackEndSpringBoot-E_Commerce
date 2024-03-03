package ecommerce.ma.appecommerce.mapper;

import ecommerce.ma.appecommerce.DTO.request.UtilisateurRequestDTO;
import ecommerce.ma.appecommerce.DTO.response.UtilisateurResponseDTO;
import ecommerce.ma.appecommerce.model.entity.Utilisateur;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurMapper {
    public UtilisateurResponseDTO fromUser(Utilisateur utilisateur) {
        UtilisateurResponseDTO utilisateurResponseDTO = new UtilisateurResponseDTO();
        BeanUtils.copyProperties(utilisateur,utilisateurResponseDTO);
        return utilisateurResponseDTO;
    }
    public Utilisateur fromUserReqDTO(UtilisateurRequestDTO utilisateurRequestDTO) {

        return Utilisateur.builder()
                .nom(utilisateurRequestDTO.getNom())
                .prenom(utilisateurRequestDTO.getPrenom())
                .adress(utilisateurRequestDTO.getAdresse())
                .role(utilisateurRequestDTO.getRole())
                .email(utilisateurRequestDTO.getEmail())
                .passwd(utilisateurRequestDTO.getPasswd())
                .build();
    }
}
