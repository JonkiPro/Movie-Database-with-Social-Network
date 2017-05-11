package com.jonki.ServiceImpl;

import com.jonki.DAO.UserCRUDRepository;
import com.jonki.DAO.UserDAO;
import com.jonki.DTO.ForgotPasswordDTO;
import com.jonki.DTO.RegisterDTO;
import com.jonki.Entity.User;
import com.jonki.Service.EncodeService;
import com.jonki.Service.RandomNumberService;
import com.jonki.Service.SendMessageService;
import com.jonki.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserCRUDRepository userCRUDRepository;
    @Autowired
    private SendMessageService sendMessageService;
    @Autowired
    private RandomNumberService randomNumberService;
    @Autowired
    private EncodeService encodeService;

    @Override
    public void registerUser(final RegisterDTO registerDTO) {
        int randomActivationCode = randomNumberService.randomActivationCode();

        sendMessageService.send(registerDTO.getEmail(), "Activation code", String.valueOf(randomActivationCode));

        userCRUDRepository.save(new User(registerDTO.getUsername(),
                                 registerDTO.getEmail(),
                                 encodeService.encode(registerDTO.getPassword()),
                                "ROLE_USER",
                                 (byte)1,
                                false,
                                 randomActivationCode));
    }

    @Override
    public User getUser(final Long id) {
        return userCRUDRepository.getOne(id);
    }

    @Override
    public List<User> getAllUsers() { return userCRUDRepository.findAll(); }

    @Override
    public boolean checkRepeatedUsername(final String username) {
        return userDAO.checkRepeatedUsername(username);
    }

    @Override
    public boolean checkRepeatedEmail(final String email) {
        return userDAO.checkRepeatedEmail(email);
    }

    @Override
    public boolean checkActivationCode(final Long id, final int code) {
        return userDAO.checkActivationCode(id, code);
    }

    @Override
    public boolean checkChangeCode(final Long id, final int code) {
        return userDAO.checkChangeCode(id, code);
    }

    @Override
    public Long getIDByUsername(final String username) {
        return userDAO.getIDByUsername(username);
    }

    @Override
    public Long getIDByEmail(final String email) {
        return userDAO.getIDByEmail(email);
    }

    @Override
    public void setName(final Long id, final String name) {
        userCRUDRepository.setName(id, name);
    }

    @Override
    public void setSecondName(final Long id, final String secondName) { userCRUDRepository.setSecondName(id, secondName); }

    @Override
    public void setLastName(final Long id, final String lastName) {
        userCRUDRepository.setLastName(id, lastName);
    }

    @Override
    public void setSex(final Long id, final String sex) {
        userCRUDRepository.setSex(id, sex);
    }

    @Override
    public void setPassword(final Long id, final String password) {
        userCRUDRepository.setPassword(id, password);
    }

    @Override
    public void setEmail(final Long id, final String email) {
        userCRUDRepository.setEmail(id, email);
    }

    @Override
    public void setNewEmail(final Long id, final String newEmail) {
        userCRUDRepository.setNewEmail(id, newEmail);
    }

    @Override
    public void setCodeChange(final Long id, final int codeChange) {
        userCRUDRepository.setCodeChange(id, codeChange);
    }

    @Override
    public void setActivationCode(final Long id, final String email) {
        int randomActivationCode = randomNumberService.randomActivationCode();
        sendMessageService.send(email,
                                "Activation code",
                                String.valueOf(randomActivationCode));

        userCRUDRepository.setActivationCode(id, randomActivationCode);
    }

    @Override
    public void setAvatar(final Long id, final String username, final MultipartFile avatar) {
        try {
            String nameAvatar = username + avatar.getOriginalFilename().substring(avatar.getOriginalFilename().indexOf("."));
            String filePath =  System.getProperty("user.home") + "/avatar/" + nameAvatar;
            File dest = new File(filePath);
            avatar.transferTo(dest);

            userCRUDRepository.setAvatar(id, "avatar/" + nameAvatar);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setUpdateDate(final Long id) {
        userCRUDRepository.setUpdateDate(id, new Date());
    }

    @Override
    public List<User> findUsersByUsernamePhrase(final String username) {
        return userCRUDRepository.findUsersByUsernamePhrase(username);
    }

    @Override
    public List<User> findUsersByEmailPhrase(final String email) {
        return userCRUDRepository.findUsersByEmailPhrase(email);
    }

    @Override
    public User findUserByUsername(final String username) {
        return userCRUDRepository.findOneByUsername(username);
    }

    @Override
    public void activationUser(final Long id) {
        userCRUDRepository.setActivationUser(id, true, 0);
    }

    @Override
    public void resetPassword(final ForgotPasswordDTO forgotPasswordDTO) {
        String randomNewPassword = randomNumberService.randomNewPassword();

        sendMessageService.send(forgotPasswordDTO.getEmail(),
                                "New password",
                                randomNewPassword);

        userCRUDRepository.setPassword(getIDByEmail(forgotPasswordDTO.getEmail()),
                                       encodeService.encode(randomNewPassword));
    }
}
