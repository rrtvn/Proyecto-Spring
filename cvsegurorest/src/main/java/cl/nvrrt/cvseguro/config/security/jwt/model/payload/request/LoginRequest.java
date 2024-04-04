package cl.nvrrt.cvseguro.config.security.jwt.model.payload.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String email;
    private String password;    
    
}
