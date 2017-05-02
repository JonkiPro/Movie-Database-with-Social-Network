package com.jonki.DTO;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegisterDTO {

    @NotEmpty
    @Pattern(regexp = "[a-zA-Z0-9_.-]{6,36}")
    private String username;

    @NotEmpty
    @Size(min = 6, max = 36)
    private String password;

    @NotEmpty
    @Size(min = 6, max = 36)
    private String reenterPassword;

    @Email
    @NotEmpty
    @Size(min = 1)
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReenterPassword() {
        return reenterPassword;
    }

    public void setReenterPassword(String reenterPassword) {
        this.reenterPassword = reenterPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
