package cl.nvrrt.cvseguro.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import cl.nvrrt.cvseguro.entities.ETipoUser;
import cl.nvrrt.cvseguro.entities.TipoUser;
import cl.nvrrt.cvseguro.entities.User;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
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
    private String tUser;
    private String password;
    private User user;
}
