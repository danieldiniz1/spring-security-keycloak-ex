package br.com.train.springkeycloack.domain.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserForm(@NotNull
                       @NotBlank(message = "Username cannot be blank")
                       @Size(min = 6, max = 20)
                       String username,
                       @NotNull
                       @NotBlank(message = "Password cannot be blank")
                       @Size(min = 8, max = 65)
                       @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]).+$",message = "Password must contain at least one uppercase and one lowercase letter")
                       String password) {
}
