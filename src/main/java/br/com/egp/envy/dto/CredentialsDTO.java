package br.com.egp.envy.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class CredentialsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String email;
    private String username;
    private String senha;

    public String getUsernameOrEmail(){
        if(email == null || email.isEmpty()){
            return username;
        } else {
            return email;
        }
    }
}
