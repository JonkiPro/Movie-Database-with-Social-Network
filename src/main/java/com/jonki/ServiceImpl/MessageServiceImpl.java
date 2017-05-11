package com.jonki.ServiceImpl;

import com.jonki.DAO.MessageCRUDRepository;
import com.jonki.DTO.MessageDTO;
import com.jonki.Entity.Message;
import com.jonki.Entity.User;
import com.jonki.Service.MessageService;
import com.jonki.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageCRUDRepository messageCRUDRepository;
    @Autowired
    private UserService userService;

    @Override
    public void sendMessage(final User author, final MessageDTO messageDTO) {
        Message message = new Message(author,
                                      userService.findUserByUsername(messageDTO.getRecipient()),
                                      messageDTO.getSubject(),
                                      messageDTO.getText());
        messageCRUDRepository.save(message);
    }

    @Override
    public Message getMessage(final Long id) {
        return messageCRUDRepository.getOne(id);
    }

    @Override
    public void deleteMessage(final Long id) {
        messageCRUDRepository.delete(id);
    }

    @Override
    public void setDateOfRead(Long id) {
        messageCRUDRepository.setDateOfRead(id, new Date());
    }
}
