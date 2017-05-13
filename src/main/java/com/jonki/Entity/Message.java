package com.jonki.Entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "text")
    private String text;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", updatable = false)
    private Date date;

    @Column(name = "dateOfRead")
    private Date dateOfRead;

    @ManyToOne
    @JoinColumn(name = "sender")
    private User senderUser;

    @ManyToOne
    @JoinColumn(name = "recipient")
    private User recipientUser;

    @Column(name = "isVisibleForSender")
    private boolean isVisibleForSender;

    @Column(name = "isVisibleForRecipient")
    private boolean isVisibleForRecipient;

    public Message() {}

    // Send message
    public Message(final User senderUser,
                   final User recipientUser,
                   final String subject,
                   final String text) {
        this.senderUser = senderUser;
        this.recipientUser = recipientUser;
        this.subject = subject;
        this.text = text;
        this.isVisibleForSender = true;
        this.isVisibleForRecipient = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateOfRead() {
        return dateOfRead;
    }

    public void setDateOfRead(Date dateOfRead) {
        this.dateOfRead = dateOfRead;
    }

    public User getSenderUser() {
        return senderUser;
    }

    public void setSenderUser(User senderUser) {
        this.senderUser = senderUser;
    }

    public User getRecipientUser() {
        return recipientUser;
    }

    public void setRecipientUser(User recipientUser) {
        this.recipientUser = recipientUser;
    }

    public boolean isVisibleForSender() {
        return isVisibleForSender;
    }

    public void setVisibleForSender(boolean visibleForSender) {
        isVisibleForSender = visibleForSender;
    }

    public boolean isVisibleForRecipient() {
        return isVisibleForRecipient;
    }

    public void setVisibleForRecipient(boolean visibleForRecipient) {
        isVisibleForRecipient = visibleForRecipient;
    }
}
