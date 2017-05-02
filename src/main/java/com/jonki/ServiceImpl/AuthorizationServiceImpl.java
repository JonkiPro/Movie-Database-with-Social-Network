package com.jonki.ServiceImpl;

import com.jonki.Entity.User;
import com.jonki.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private CookieService cookieService;
    @Autowired
    private SecurityUserService securityUserService;
    @Autowired
    private UserService userService;

    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @Override
    public void setRequestSessionSecurity(final HttpServletRequest request,
                                          final HttpSession session,
                                          final SecurityContext securityContext) {
        this.request = request;
        this.session = session;
        securityUserService.setContext(securityContext);
    }

    @Override
    public void setRequestResponseSessionSecurity(final HttpServletRequest request,
                                                  final HttpServletResponse response,
                                                  final HttpSession session,
                                                  final SecurityContext securityContext) {
        this.request = request;
        this.response = response;
        this.session = session;
        securityUserService.setContext(securityContext);
    }

    @Override
    public boolean isLogged() {
        if (cookieService.isCookie(request, "username")
                && session.getAttribute("user") != null) {
            return true;
        } else if (cookieService.isCookie(request, "username")) {
            restoreUser();

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isLoginProcess() {
        return !cookieService.isCookie(request, "username")
                && !securityUserService.isAnonymousUser();
    }

    @Override
    public void login() {
        String username = securityUserService.getUsername();

        User user = userService.findUserByUsername(username);
        session.setAttribute("user", user);

        cookieService.addCookie(response, "username", user.getUsername());
    }

    @Override
    public User getUser() {
        return userService.findUserByUsername(securityUserService.getUsername());
    }

    @Override
    public void checkOrRestoreUser() {
        if (cookieService.isCookie(request, "username")
                && session.getAttribute("user") == null) {
            restoreUser();
        }
    }

    private void restoreUser() {
        String usernameFromCookie = cookieService.getValueCookie(request, "username");

        User user = userService.findUserByUsername(usernameFromCookie);

        session.setAttribute("user", user);

        securityUserService.createUsernamePasswordAuthenticationToken(user.getUsername(),
                null,
                AuthorityUtils.createAuthorityList("ROLE_USER"));
    }
}
