package com.jonki.Controller;

import com.jonki.DTO.ForgotPasswordDTO;
import com.jonki.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/forgotPassword")
public class ForgotPasswordController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthorizationService authorizationService;

    @GetMapping
    public String loadForgotPasswordPage(final HttpServletRequest request,
                                         final HttpSession session,
                                         final Model model) {
        authorizationService.setRequestSessionSecurity(request, session, SecurityContextHolder.getContext());

        if (authorizationService.isLogged()) {
            return "redirect:/";
        }

        model.addAttribute("forgotPasswordDTO", new ForgotPasswordDTO());

        return "forgotPassword";
    }

    @PostMapping
    public String changePassword(@ModelAttribute("forgotPasswordDTO") @Valid final ForgotPasswordDTO forgotPasswordDTO,
                                 final BindingResult bindingResult,
                                 final Model model) {
        if(!userService.checkRepeatedEmail(forgotPasswordDTO.getEmail())
                || bindingResult.hasErrors()) {
            model.addAttribute("invalidEmail", true);
            model.addAttribute("forgotPasswordDTO", forgotPasswordDTO);
            model.addAttribute("hasError", "has-error");
            return "forgotPassword";
        }

        userService.resetPassword(forgotPasswordDTO);

        return "forgotPassword";
    }
}
