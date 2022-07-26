package com.rtkit.fifth.element.kms.model.dto;

import com.rtkit.fifth.element.kms.model.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationInfo {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 5, max = 20,
            message = "Не меньше 5 знаков и не более 20 знаков")
    private String name;

    @NotBlank
    @Size(min = 5, max = 20,
            message = "Не меньше 5 знаков и не более 20 знаков")
    private String password;

    @NotBlank
    @Size(min = 5, max = 20,
            message = "Не меньше 5 знаков и не более 20 знаков")
    private String passwordConfirm;

    private Role role;
}
