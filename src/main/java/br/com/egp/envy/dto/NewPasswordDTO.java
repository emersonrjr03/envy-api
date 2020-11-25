package br.com.egp.envy.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class NewPasswordDTO {
    @NotEmpty(message = "Mandatory field")
    @Email(message = "Invalid email address")
    private Integer userId;
    @NotEmpty(message = "Mandatory field")
    private String newPassword;
    @NotEmpty(message = "Mandatory field")
    private String previousPassword;
}
