package ecommerce.ma.appecommerce.DTO.response;

import ecommerce.ma.appecommerce.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDTO {
    private Long id;
    private String email;
    private String passwd;
    @Enumerated(EnumType.STRING)
    private Role role;
}
