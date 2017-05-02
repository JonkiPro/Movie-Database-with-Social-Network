package com.jonki.Service;

import com.jonki.Entity.User;
import org.springframework.security.core.context.SecurityContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface AuthorizationService {
    public void setRequestSessionSecurity(final HttpServletRequest request,
                                          final HttpSession session,
                                          final SecurityContext securityContext);
    public void setRequestResponseSessionSecurity(final HttpServletRequest request,
                                                  final HttpServletResponse response,
                                                  final HttpSession session,
                                                  final SecurityContext securityContext);
    public boolean isLogged();
    public boolean isLoginProcess();
    public void login();
    public User getUser();
    public void checkOrRestoreUser();
}
