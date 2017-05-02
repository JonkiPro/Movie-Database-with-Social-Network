package com.jonki.DTO;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class LoginDTO {

    @NotEmpty
    @Size(min = 6, max = 36)
    private String username;

    @NotEmpty
    @Size(min = 6, max = 36)
    private String password;

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
}
