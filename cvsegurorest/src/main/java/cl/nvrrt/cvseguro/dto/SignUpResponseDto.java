package cl.nvrrt.cvseguro.dto;

import lombok.Data;

@Data
public class SignUpResponseDto {
    private String id;
    private String name;
    private String email;
    private String password;
    private String role;
    
    private String token;
    private String error;
    private String message;
}
