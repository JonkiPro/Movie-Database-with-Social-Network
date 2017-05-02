package com.jonki.DTO;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;

public class ChangeBasicDataDTO {

    @Pattern(regexp = "[a-zA-Z0-9]{0}|[a-zA-Z0-9]{6,36}")
    private String name;

    @Pattern(regexp = "[a-zA-Z0-9]{0}|[a-zA-Z0-9]{6,36}")
    private String secondName;

    @Pattern(regexp = "[a-zA-Z0-9]{0}|[a-zA-Z0-9]{6,36}")
    private String lastName;

    private String sex;

    private MultipartFile multipartFile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
