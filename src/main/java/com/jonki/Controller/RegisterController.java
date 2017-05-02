package com.jonki.Controller;

import com.jonki.DTO.RegisterDTO;
import com.jonki.Service.*;
import com.jonki.Validator.Validator;
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
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;
    @Autowired
    private Validator validator;
    @Autowired
    private AuthorizationService authorizationService;

    @GetMapping
    public String register(final HttpServletRequest request,
                           final HttpSession session,
                           final Model model) {
        authorizationService.setRequestSessionSecurity(request, session, SecurityContextHolder.getContext());

        if (authorizationService.isLogged()) {
            return "redirect:/";
        }

        model.addAttribute("registerDTO", new RegisterDTO());

        return "register";
    }

    @PostMapping
    public String registerCheck(@ModelAttribute("registerDTO") @Valid final RegisterDTO registerDTO,
                                final BindingResult result,
                                final Model model,
                                final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            if (!validator.checkUsername(registerDTO.getUsername())) {
                model.addAttribute("invalidData", true);
                model.addAttribute("textError", "The username is incorrect!");
                model.addAttribute("registerDTO", registerDTO);
                model.addAttribute("invalidUsername", true);
                return "register";
            } else if (!validator.checkEmail(registerDTO.getEmail())) {
                model.addAttribute("invalidData", true);
                model.addAttribute("textError", "The e-mail is incorrect!");
                model.addAttribute("registerDTO", registerDTO);
                model.addAttribute("invalidEmail", true);
                return "register";
            } else if (!validator.checkPassword(registerDTO.getPassword())) {
                model.addAttribute("invalidData", true);
                model.addAttribute("textError", "The password is incorrect!");
                model.addAttribute("registerDTO", registerDTO);
                model.addAttribute("invalidPassword", true);
                return "register";
            }
        }
        if (userService.checkRepeatedUsername(registerDTO.getUsername())) {
            model.addAttribute("invalidData", true);
            model.addAttribute("textError", "The given username is in the database!");
            model.addAttribute("registerDTO", registerDTO);
            model.addAttribute("invalidUsername", true);
            return "register";
        } else if (userService.checkRepeatedEmail(registerDTO.getEmail())) {
            model.addAttribute("invalidData", true);
            model.addAttribute("textError", "The given email is in the database!");
            model.addAttribute("registerDTO", registerDTO);
            model.addAttribute("invalidEmail", true);
            return "register";
        } else if (!validator.checkReenterPassword(registerDTO.getPassword(), registerDTO.getReenterPassword())) {
            model.addAttribute("invalidData", true);
            model.addAttribute("textError", "Passwords are not the same!");
            model.addAttribute("registerDTO", registerDTO);
            model.addAttribute("invalidReenterPassword", true);
            return "register";
        }

        userService.registerUser(registerDTO);

        redirectAttributes.addFlashAttribute("afterRegistration", true);
        return "redirect:/login";
    }
}
