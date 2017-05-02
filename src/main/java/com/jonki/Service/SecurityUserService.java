package com.jonki.Service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;

import java.util.Collection;

public interface SecurityUserService {
    public void setContext(final SecurityContext context);
    public String getUsername();
    public void createUsernamePasswordAuthenticationToken(final String username,
                                                          final String password,
                                                          final Collection<? extends GrantedAuthority> authorities);
    public boolean isAnonymousUser();
}
