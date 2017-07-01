package com.jonki.Controller;

import com.jonki.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ActivationCodeController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthorizationService authorizationService;

    @Secured("ROLE_USER")
    @GetMapping("/checkCode")
    public String showPage(final HttpServletRequest request,
                           final HttpSession session) {
        authorizationService.setRequestSessionSecurity(request, session, SecurityContextHolder.getContext());

        if(authorizationService.isLogged()) {
            return "redirect:/";
        }

        SecurityContextHolder.clearContext();

        return "checkActivationCode";
    }

    @PostMapping("/checkCode")
    public String checkCode(@RequestParam("code") final String code,
                            final Model model,
                            final HttpServletRequest request,
                            final HttpSession session) {
        authorizationService.setRequestSessionSecurity(request, session, SecurityContextHolder.getContext());

        int activationCode;
        try { activationCode = Integer.parseInt(code); } catch(NumberFormatException e) { activationCode = 0; }

        if(userService.checkActivationCode((long)session.getAttribute("userID"), activationCode)) {
            userService.activationUser((long)session.getAttribute("userID"));
            authorizationService.restoreUserAfterActivationAccount((long)session.getAttribute("userID"));
            session.removeAttribute("userID");
            session.removeAttribute("userEmail");
            return "redirect:/loginSuccessfully";
        } else {
            model.addAttribute("codeInvited",true);
            return "checkActivationCode";
        }
    }

    @PostMapping("/sendNewCode")
    public String sendNewCode(final HttpSession session,
                              final Model model) {
        userService.setActivationCode((long)session.getAttribute("userID"),
                                      String.valueOf(session.getAttribute("userEmail")));

        model.addAttribute("isNewCode", true);
        return "checkActivationCode";
    }
}
