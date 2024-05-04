package cl.nvrrt.cvseguro.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cl.nvrrt.cvseguro.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReqRes {
    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    
    private String fecha;
    private String name;
    private String username;
    private String lastname;
    private String direccion;
    private String email;
    private String role;
    private String password;
    private User user;
}
