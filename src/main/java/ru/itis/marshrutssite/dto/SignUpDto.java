package ru.itis.marshrutssite.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

@Data
public class SignUpDto {

    @NotNull()
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Только буквы!")
    private String name;
    @Email()
    private String email;
    @NotNull()
    @Size(min = 8,max = 16)
    private String password;
}
