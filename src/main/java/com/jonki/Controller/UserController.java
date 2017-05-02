package com.jonki.Controller;

import com.jonki.DTO.ChangeBasicDataDTO;
import com.jonki.DTO.ChangeEmailDTO;
import com.jonki.DTO.ChangePasswordDTO;
import com.jonki.DTO.FilterUsersDTO;
import com.jonki.Entity.User;
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
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private Validator validator;
    @Autowired
    private EncodeService encodeService;
    @Autowired
    private SendMessageService sendMessageService;
    @Autowired
    private RandomNumberService randomNumberService;

    @GetMapping("/user/{id}")
    public String showUser(@PathVariable("id") final Long id,
                           final HttpServletRequest request,
                           final HttpSession session,
                           final Model model) {
        authorizationService.setRequestSessionSecurity(request, session, SecurityContextHolder.getContext());
        User user = userService.getUser(id);

        if (user == null) {
            authorizationService.checkOrRestoreUser();

            return "redirect:/";
        }

        if (authorizationService.isLogged()) {
            if (((User) session.getAttribute("user")).getId().equals(user.getId())) {
                model.addAttribute("isMyAccount", true);
            } else {
                model.addAttribute("isMyAccount", false);
            }
        }

        model.addAttribute("user", user);

        return "user";
    }

    @GetMapping("/listUsers")
    public String showListUsers(@RequestParam(value = "search", required = false) final String search,
                                @RequestParam(value = "filter", required = false) final String filter,
                                final Model model,
                                final HttpServletRequest request,
                                final HttpSession session) {
        authorizationService.setRequestSessionSecurity(request, session, SecurityContextHolder.getContext());

        if(authorizationService.isLogged()) {
            model.addAttribute("isLogged", true);
        } else {
            model.addAttribute("isLogged", false);
        }

        if (search == null) {
            List<User> listUsers = userService.getAllUsers();

            model.addAttribute("listUsers", listUsers);
            model.addAttribute("numberOfUsers", listUsers.size());
            model.addAttribute("filterUsersDTO", new FilterUsersDTO());
        } else {
            List<User> listUsers;
            if (filter.equals("username")) {
                listUsers = userService.findUsersByUsernamePhrase("%" + search + "%");
                model.addAttribute("filterByUsername", true);
            } else {
                listUsers = userService.findUsersByEmailPhrase("%" + search + "%");
                model.addAttribute("filterByEmail", true);
            }

            model.addAttribute("listUsers", listUsers);
            model.addAttribute("isSearch", true);
            model.addAttribute("search", search);
            model.addAttribute("filter", filter);
            model.addAttribute("numberOfUsers", listUsers.size());
            model.addAttribute("filterUsersDTO", new FilterUsersDTO());
            model.addAttribute("initSearch", search);
        }

        return "listUsers";
    }

    @PostMapping("/listUsers")
    public String showListUsersAfterFilter(@ModelAttribute("filterUsersDTO") final FilterUsersDTO filterUsersDTO) {
        return "redirect:/listUsers?search=" + filterUsersDTO.getSearch() + "&filter=" + filterUsersDTO.getFilter();
    }

    @GetMapping("/user/settings")
    public String settings(final HttpServletRequest request,
                           final HttpSession session) {
        authorizationService.setRequestSessionSecurity(request, session, SecurityContextHolder.getContext());

        if (!authorizationService.isLogged()) {
            return "redirect:/";
        }

        return "settings";
    }

    @GetMapping("/user/settings/changeBasicData")
    public String settingsBasicData(final HttpServletRequest request,
                                    final HttpSession session,
                                    final Model model) {
        authorizationService.setRequestSessionSecurity(request, session, SecurityContextHolder.getContext());

        if (!authorizationService.isLogged()) {
            return "redirect:/";
        }

        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("changeBasicDataDTO", new ChangeBasicDataDTO());

        return "changeBasicData";
    }

    @PostMapping("/user/settings/changeBasicData")
    public String changeBasicData(@ModelAttribute("changeBasicDataDTO") @Valid final ChangeBasicDataDTO changeBasicDataDTO,
                                  final BindingResult bindingResult,
                                  final Model model,
                                  final HttpSession session) {
        if (bindingResult.hasErrors()
                || (changeBasicDataDTO.getMultipartFile().getOriginalFilename().length() > 0
                        && !validator.checkAvatar(changeBasicDataDTO.getMultipartFile()))) {
            model.addAttribute("invalidData", true);
            return "changeBasicData";
        } else {
            model.addAttribute("successData", true);

            User user = (User) session.getAttribute("user");
            user.setName(changeBasicDataDTO.getName());
            user.setSecondName(changeBasicDataDTO.getSecondName());
            user.setLastName(changeBasicDataDTO.getLastName());
            user.setSex(changeBasicDataDTO.getSex());

            userService.setName(user.getId(), changeBasicDataDTO.getName());
            userService.setSecondName(user.getId(), changeBasicDataDTO.getSecondName());
            userService.setLastName(user.getId(), changeBasicDataDTO.getLastName());
            userService.setSex(user.getId(), changeBasicDataDTO.getSex());
            if(changeBasicDataDTO.getMultipartFile().getOriginalFilename().length() > 0) {
                userService.setAvatar(user.getId(), user.getUsername(), changeBasicDataDTO.getMultipartFile());
            }
            userService.setUpdateDate(user.getId());

            return "changeBasicData";
        }
    }

    @GetMapping("/user/settings/changePassword")
    public String settingsPassword(final HttpServletRequest request,
                                   final HttpSession session,
                                   final Model model) {
        authorizationService.setRequestSessionSecurity(request, session, SecurityContextHolder.getContext());

        if (!authorizationService.isLogged()) {
            return "redirect:/";
        }

        model.addAttribute("changePasswordDTO", new ChangePasswordDTO());

        return "changePassword";
    }

    @PostMapping("/user/settings/changePassword")
    public String changePassword(@ModelAttribute("changePasswordDTO") @Valid final ChangePasswordDTO changePasswordDTO,
                                 final BindingResult bindingResult,
                                 final Model model,
                                 final HttpSession session) {
        if (!encodeService.matches(changePasswordDTO.getOldPassword(), ((User) session.getAttribute("user")).getPassword())) {
            model.addAttribute("textError", " Old password is incorrect!");
            model.addAttribute("invalidPassword", true);
            model.addAttribute("invalidOldPassword", true);
            return "changePassword";
        } else if (!validator.checkPassword(changePasswordDTO.getNewPassword())) {
            model.addAttribute("textError", " New password is incorrect!");
            model.addAttribute("invalidPassword", true);
            return "changePassword";
        } else if (!validator.checkReenterPassword(changePasswordDTO.getNewPassword(),
                changePasswordDTO.getRepeatNewPassword())) {
            model.addAttribute("textError", " Passwords are not the same!");
            model.addAttribute("invalidPassword", true);
            return "changePassword";
        }

        model.addAttribute("successData", true);

        String encodePassword = encodeService.encode(changePasswordDTO.getNewPassword());

        User user = (User) session.getAttribute("user");
        user.setPassword(encodePassword);

        userService.setPassword(user.getId(), encodePassword);
        userService.setUpdateDate(user.getId());

        return "changePassword";
    }

    @GetMapping("/user/settings/changeEmail")
    public String settingsEmail(final HttpServletRequest request,
                                final HttpSession session,
                                final Model model) {
        authorizationService.setRequestSessionSecurity(request, session, SecurityContextHolder.getContext());

        if (authorizationService.isLogged()) {
            if(!(((User) session.getAttribute("user")).getCodeChange() == 0)) {
                model.addAttribute("isChangeEmail", true);
            }
        } else {
            return "redirect:/";
        }

        model.addAttribute("changeEmailDTO", new ChangeEmailDTO());

        return "changeEmail";
    }

    @PostMapping("/user/settings/changeEmail")
    public String changeEmail(@ModelAttribute("changeEmailDTO") @Valid final ChangeEmailDTO changeEmailDTO,
                              final BindingResult bindingResult,
                              final Model model,
                              final HttpSession session) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("invalid", true);
            model.addAttribute("textError", " New e-mail address is invalid!");
            return "changeEmail";
        } else {
            if(userService.checkRepeatedEmail(changeEmailDTO.getNewEmail())) {
                model.addAttribute("invalid", true);
                model.addAttribute("textError", " The given email is in the database!");
                return "changeEmail";
            }

            int randomActivationCode = randomNumberService.randomActivationCode();

            if(sendMessageService.send(((User) session.getAttribute("user")).getEmail(),
                                      "E-mail change code",
                                       String.valueOf(randomActivationCode))) {
                User user = (User) session.getAttribute("user");
                user.setNewEmail(changeEmailDTO.getNewEmail());
                user.setCodeChange(randomActivationCode);

                userService.setNewEmail(user.getId(), changeEmailDTO.getNewEmail());
                userService.setCodeChange(user.getId(), randomActivationCode);
                userService.setUpdateDate(user.getId());

                model.addAttribute("success", true);
                model.addAttribute("textSuccess", " E-mail change code has been sent to your mail!");
                model.addAttribute("isChangeEmail", true);
                return "changeEmail";
            } else {
                model.addAttribute("invalid", true);
                model.addAttribute("textError", " E-mail doesn't exist!");
                return "changeEmail";
            }
        }
    }

    @PostMapping("/user/settings/changeEmail/checkCode")
    public String checkChangeCode(@RequestParam("code") final String code,
                                  final RedirectAttributes redirectAttributes,
                                  final HttpSession session) {
        int changeCode;
        try { changeCode = Integer.parseInt(code); } catch(NumberFormatException e) { changeCode = 0; }

        if(userService.checkChangeCode(((User)session.getAttribute("user")).getId(), changeCode)) {
            User user = (User) session.getAttribute("user");

            userService.setEmail(user.getId(), user.getNewEmail());
            userService.setNewEmail(user.getId(), "");
            userService.setCodeChange(user.getId(), 0);
            userService.setUpdateDate(user.getId());

            user.setEmail(user.getNewEmail());
            user.setNewEmail("");
            user.setCodeChange(0);

            redirectAttributes.addFlashAttribute("success", true);
            redirectAttributes.addFlashAttribute("textSuccess", " E-mail changed!");
            return "redirect:/user/settings/changeEmail";
        } else {
            redirectAttributes.addFlashAttribute("isChangeEmail", true);
            redirectAttributes.addFlashAttribute("invalidCode", true);
            redirectAttributes.addFlashAttribute("invalid", true);
            redirectAttributes.addFlashAttribute("textError", " You entered the wrong code!");
            return "redirect:/user/settings/changeEmail";
        }
    }
}
