package cl.nvrrt.cvseguro.entities;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Login {


    private String email;
    private String password;    

    @Builder
    public Login(String email, String password) {
        this.email = email;
        this.password = password;        
    }
}
