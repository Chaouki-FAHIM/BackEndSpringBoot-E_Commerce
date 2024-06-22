package ecommerce.ma.appecommerce.DTO.response;

import ecommerce.ma.appecommerce.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDTO {
    private Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}
