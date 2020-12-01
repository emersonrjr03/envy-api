package br.com.egp.envy.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class NewPasswordDTO {
    @NotNull(message = "Mandatory field")
    private Integer userId;
    @NotEmpty(message = "Mandatory field")
    private String newPassword;
    @NotEmpty(message = "Mandatory field")
    private String previousPassword;
}
