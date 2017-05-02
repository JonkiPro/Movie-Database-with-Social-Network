package com.jonki.ServiceImpl;

import com.jonki.DAO.GenericDAO;
import com.jonki.DAO.MessageDAO;
import com.jonki.DTO.MessageDTO;
import com.jonki.Entity.Message;
import com.jonki.Entity.User;
import com.jonki.Service.MessageService;
import com.jonki.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private GenericDAO genericDAO;
    @Autowired
    private UserService userService;

    @Override
    public void sendMessage(final User author, final MessageDTO messageDTO) {
        Message message = new Message(author,
                                      userService.findUserByUsername(messageDTO.getRecipient()),
                                      messageDTO.getSubject(),
                                      messageDTO.getText());
        genericDAO.save(message);
    }
}
