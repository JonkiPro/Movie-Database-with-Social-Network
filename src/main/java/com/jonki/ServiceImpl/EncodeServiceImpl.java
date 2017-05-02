package com.jonki.ServiceImpl;

import com.jonki.Service.EncodeService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncodeServiceImpl implements EncodeService {

    @Override
    public String encode(String password) {
        return new BCryptPasswordEncoder(12).encode(password);
    }

    @Override
    public boolean matches(String password1, String password2) {
        return new BCryptPasswordEncoder(12).matches(password1, password2);
    }
}
