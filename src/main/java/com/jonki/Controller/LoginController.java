package com.jonki.Controller;

import com.jonki.Service.AuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private AuthorizationService authorizationService;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(final HttpServletRequest request,
                        final HttpSession session) {
        authorizationService.setRequestSessionSecurity(request, session, SecurityContextHolder.getContext());

        if (authorizationService.isLogged()) {
            return "redirect:/";
        }

        logger.info("/login");
        return "login";
    }

    @RequestMapping(value = "/loginSuccessfully", method = RequestMethod.GET)
    public String loginSuccessfully(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final HttpSession session) {
        authorizationService.setRequestResponseSessionSecurity(request, response, session, SecurityContextHolder.getContext());

        if (authorizationService.isLoginProcess()) {
            if(!authorizationService.getUser().isActivation()) {
                session.setAttribute("userID", authorizationService.getUser().getId());
                session.setAttribute("userEmail", authorizationService.getUser().getEmail());
                return "redirect:/checkCode";
            }
            authorizationService.login();
            logger.info("/loginSuccessfully login process");
        } else if (!authorizationService.isLogged()) {
            logger.info("/loginSuccessfully go to /login");
            return "redirect:/login";
        }

        logger.info("/loginSuccessfully");
        return "redirect:/";
    }
}
