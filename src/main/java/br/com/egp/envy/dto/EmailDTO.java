package br.com.egp.envy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class EmailDTO implements Serializable {
    private static final long serialVersionUID = 1;
    @NotEmpty(message = "Mandatory field")
    @Email(message = "Invalid email address")
    private String email;

}
