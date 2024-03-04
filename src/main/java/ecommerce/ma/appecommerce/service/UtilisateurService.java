package ecommerce.ma.appecommerce.service;

import ecommerce.ma.appecommerce.DTO.request.LoginRequestDTO;
import ecommerce.ma.appecommerce.DTO.request.UtilisateurRequestDTO;
import ecommerce.ma.appecommerce.DTO.response.LoginResponseDTO;
import ecommerce.ma.appecommerce.DTO.response.UtilisateurResponseDTO;
import ecommerce.ma.appecommerce.exception.UserCreationException;
import ecommerce.ma.appecommerce.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;

public interface UtilisateurService extends IService<UtilisateurRequestDTO, UtilisateurResponseDTO,Long> {
    LoginResponseDTO login(LoginRequestDTO loginDTO) throws UserNotFoundException;
    UtilisateurResponseDTO register(UtilisateurRequestDTO utilisateurRequestDTO) throws UserCreationException;
}
