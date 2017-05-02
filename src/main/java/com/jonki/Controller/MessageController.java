package com.jonki.Controller;

import com.jonki.DTO.MessageDTO;
import com.jonki.Entity.User;
import com.jonki.Service.AuthorizationService;
import com.jonki.Service.MessageService;
import com.jonki.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class MessageController {

    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private AuthorizationService authorizationService;

    @GetMapping("/sendMessage")
    public String showSendMessage(@RequestParam(value = "recipient", required = false) final String username,
                                  final Model model,
                                  final HttpServletRequest request,
                                  final HttpSession session) {
        authorizationService.setRequestSessionSecurity(request, session, SecurityContextHolder.getContext());

        if(!authorizationService.isLogged()) {
            return "redirect:/";
        }

        if(!userService.checkRepeatedUsername(username)) {
            return "redirect:/";
        }

        model.addAttribute("messageDTO", new MessageDTO());
        model.addAttribute("recipient", username);

        return "sendMessage";
    }

    @PostMapping("/sendMessage")
    public String sendMessage(@ModelAttribute("messageDTO") @Valid MessageDTO messageDTO,
                              final BindingResult bindingResult,
                              final Model model,
                              final HttpSession session) {
        model.addAttribute("messageDTO", messageDTO);
        model.addAttribute("recipient", messageDTO.getRecipient());

        if(!userService.checkRepeatedUsername(messageDTO.getRecipient())) {
            model.addAttribute("invalidRecipient", true);
            return "sendMessage";
        }

        if(bindingResult.hasErrors()) {
            return "sendMessage";
        }

        messageService.sendMessage((User) session.getAttribute("user"), messageDTO);

        model.addAttribute("successSendMessage", true);
        return "sendMessage";
    }
}
