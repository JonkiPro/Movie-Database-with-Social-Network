package com.jonki.Entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @NotEmpty
    @Column(name = "username")
    private String username;

    @NotEmpty
    @Column(name = "email")
    private String email;

    @NotEmpty
    @Column(name = "password")
    private String password;

    @Column(name = "‘ROLE_USER’")
    private String authorities;

    @Column(name = "enabled")
    private byte enabled;

    @Column(name = "name")
    private String name;

    @Column(name = "secondName")
    private String secondName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "sex")
    private String sex;

    @Column(name = "activation")
    private boolean activation;

    @Column(name = "activationCode")
    private int activationCode;

    @Column(name = "urlAvatar")
    private String urlAvatar;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createDate", updatable = false)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updateDate")
    private Date updateDate;

    //// If user want to change e-mail

    @Column(name = "newEmail")
    private String newEmail;

    @Column(name = "codeChange")
    private int codeChange;

    //////////////////////////////////

    @OneToMany(mappedBy = "senderUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "recipientUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> receivedMessages;

    //////////////////////////////////

    @ManyToMany
    @JoinTable(
            name = "friendship",
            joinColumns = {@JoinColumn(name = "friendB")},
            inverseJoinColumns = {@JoinColumn(name = "friendA")}
    )
    private List<User> friends;

    @ManyToMany
    @JoinTable(
            name = "invitations",
            joinColumns = {@JoinColumn(name = "toID")},
            inverseJoinColumns = {@JoinColumn(name = "fromID")}
    )
    private List<User> sentInvitations;

    @ManyToMany
    @JoinTable(
            name = "invitations",
            joinColumns = {@JoinColumn(name = "fromID")},
            inverseJoinColumns = {@JoinColumn(name = "toID")}
    )
    private List<User> receivedInvitations;

    //////////////////////////////////

    public User() {}

    // Register
    public User(String username,
                String email,
                String password,
                String authorities,
                byte enabled,
                boolean activation,
                int activationCode) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;
        this.activation = activation;
        this.activationCode = activationCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public byte getEnabled() {
        return enabled;
    }

    public void setEnabled(byte enabled) {
        this.enabled = enabled;
    }

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

    public boolean isActivation() {
        return activation;
    }

    public void setActivation(boolean activation) {
        this.activation = activation;
    }

    public int getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(int activationCode) {
        this.activationCode = activationCode;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public int getCodeChange() {
        return codeChange;
    }

    public void setCodeChange(int codeChange) {
        this.codeChange = codeChange;
    }

    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(List<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(List<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public List<User> getSentInvitations() {
        return sentInvitations;
    }

    public void setSentInvitations(List<User> sentInvitations) {
        this.sentInvitations = sentInvitations;
    }

    public List<User> getReceivedInvitations() {
        return receivedInvitations;
    }

    public void setReceivedInvitations(List<User> receivedInvitations) {
        this.receivedInvitations = receivedInvitations;
    }
}
