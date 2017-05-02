package com.jonki.DTO;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class MessageDTO {

    private String recipient;

    @NotEmpty
    @Size(min = 1)
    private String subject;

    @NotEmpty
    @Size(min = 1)
    private String text;

    private String sender;

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
