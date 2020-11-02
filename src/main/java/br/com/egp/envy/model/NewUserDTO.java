package br.com.egp.envy.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class NewUserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty(message = "Mandatory field")
    @Length(min = 5, max = 120, message = "Name size must be 5 to 120 characters long")
    private String name;
    @NotEmpty(message = "Mandatory field")
    @Email(message = "Invalid email address")
    private String email;
    @NotEmpty(message = "Mandatory field")
    @Length(min = 5, max = 20, message = "Username size must be 5 to 20 characters long")
    private String username;
    @NotEmpty(message = "Mandatory field")
    private String password;
    @NotEmpty(message = "Mandatory field")
    private Date birthDate;

}
