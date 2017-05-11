package com.jonki.Service;

import com.jonki.DTO.MessageDTO;
import com.jonki.Entity.Message;
import com.jonki.Entity.User;

public interface MessageService {

    // Message CRUD Repository // SPRING DATA
    public void sendMessage(final User author, final MessageDTO messageDTO);
    public Message getMessage(final Long id);
    public void deleteMessage(final Long id);
    public void setDateOfRead(final Long id);
}
