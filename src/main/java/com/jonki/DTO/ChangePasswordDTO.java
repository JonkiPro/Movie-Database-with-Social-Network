package com.jonki.DTO;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class ChangePasswordDTO {

    @NotEmpty
    @Size(min = 6, max = 36)
    private String oldPassword;

    @NotEmpty
    @Size(min = 6, max = 36)
    private String newPassword;

    @NotEmpty
    @Size(min = 6, max = 36)
    private String repeatNewPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatNewPassword() {
        return repeatNewPassword;
    }

    public void setRepeatNewPassword(String repeatNewPassword) {
        this.repeatNewPassword = repeatNewPassword;
    }
}
