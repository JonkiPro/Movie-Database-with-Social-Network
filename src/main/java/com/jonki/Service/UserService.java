package com.jonki.Service;

import com.jonki.DTO.ForgotPasswordDTO;
import com.jonki.DTO.RegisterDTO;
import com.jonki.Entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    // User DAO // HIBERNATE
    public boolean checkRepeatedUsername(final String username);
    public boolean checkRepeatedEmail(final String email);
    public boolean checkActivationCode(final Long id, final int code);
    public boolean checkChangeCode(final Long id, final int code);
    public Long getIDByUsername(final String username);
    public Long getIDByEmail(final String email);

    // User CRUD Repository // SPRING DATA
    public void registerUser(final RegisterDTO registerDTO);
    public User getUser(final Long id);
    public List<User> getAllUsers();
    public void setName(final Long id, final String name);
    public void setSecondName(final Long id, final String secondName);
    public void setLastName(final Long id, final String lastName);
    public void setSex(final Long id, final String sex);
    public void setPassword(final Long id, final String password);
    public void setEmail(final Long id, final String email);
    public void setNewEmail(final Long id, final String newEmail);
    public void setCodeChange(final Long id, final int codeChange);
    public void setActivationCode(final Long id, final String email);
    public void setAvatar(final Long id, final String username, final MultipartFile avatar);
    public void setUpdateDate(final Long id);
    public List<User> findUsersByUsernamePhrase(final String username);
    public List<User> findUsersByEmailPhrase(final String email);
    public User findUserByUsername(final String username);
    public void activationUser(final Long id);
    public void resetPassword(final ForgotPasswordDTO forgotPasswordDTO);
}
