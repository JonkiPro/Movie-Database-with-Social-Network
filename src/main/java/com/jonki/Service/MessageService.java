package com.jonki.Service;

import com.jonki.DTO.MessageDTO;
import com.jonki.Entity.User;

public interface MessageService {

    // Generic DAO // SPRING DATA
    public void sendMessage(final User author, final MessageDTO messageDTO);
}
