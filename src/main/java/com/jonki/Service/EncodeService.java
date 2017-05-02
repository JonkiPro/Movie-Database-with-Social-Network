package com.jonki.Service;

public interface EncodeService {
    public String encode(String password);
    public boolean matches(final String password1, final String password2);
}
