package com.jonki.ServiceImpl;

import com.jonki.Service.SecurityUserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SecurityUserServiceImpl implements SecurityUserService {

    private SecurityContext context;

    @Override
    public void setContext(final SecurityContext context) {
        this.context = context;
    }

    @Override
    public String getUsername() {
        String username;

        try {
            username = ((UserDetails) this.context.getAuthentication().getPrincipal()).getUsername();
        } catch (ClassCastException e) {
            username = this.context.getAuthentication().getPrincipal().toString();
        }
        return username;
    }

    @Override
    public void createUsernamePasswordAuthenticationToken(final String username,
                                                          final String password,
                                                          final Collection<? extends GrantedAuthority> authorities) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;
        usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,
                                                                                      password,
                                                                                      authorities);
        this.context.setAuthentication(usernamePasswordAuthenticationToken);
    }

    @Override
    public boolean isAnonymousUser() {
        return this.context.getAuthentication().getPrincipal().toString().equals("anonymousUser");
    }
}
