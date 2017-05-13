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
import java.util.List;

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
    public void setDateOfRead(Long id) {
        messageCRUDRepository.setDateOfRead(id, new Date());
    }

    @Override
    public void deleteReceivedMessage(final Long id) {
        messageCRUDRepository.deleteReceivedMessage(id, false);
    }

    @Override
    public void deleteSentMessage(final Long id) {
        messageCRUDRepository.deleteSentMessage(id, false);
    }

    @Override
    public List<Message> findAllReceivedMessages(final User recipient) {
        return messageCRUDRepository.findAllReceivedMessages(true, recipient);
    }

    @Override
    public List<Message> findAllSentMessages(final User sender) {
        return messageCRUDRepository.findAllSentMessages(true, sender);
    }
}
