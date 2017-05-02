package com.jonki.Validator;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.regex.Pattern;

@Component
public class Validator {
    public boolean checkUsername(final String username) {
        return username.matches("[a-zA-Z0-9_.-]{6,36}");
    }

    public boolean checkEmail(final String email) {
        return email.length() > 1;
    }

    public boolean checkPassword(final String password) {
        return password.matches("^\\w{6,36}$");
    }

    public boolean checkReenterPassword(final String password, final String reenterPassword) {
        return password.equals(reenterPassword);
    }

    public boolean checkAvatar(final MultipartFile multipartFile) {
        return multipartFile.getOriginalFilename().endsWith(".jpg")
                || multipartFile.getOriginalFilename().endsWith(".png");
    }
}
