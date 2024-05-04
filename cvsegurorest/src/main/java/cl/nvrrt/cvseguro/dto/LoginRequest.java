package cl.nvrrt.cvseguro.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String email;
    private String password;    

    private String token;
    private String error;
    private String message;
    
}
