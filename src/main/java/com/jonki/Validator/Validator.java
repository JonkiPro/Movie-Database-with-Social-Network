package com.jonki.Validator;

import com.jonki.Entity.Friendship;
import com.jonki.Entity.Invitation;
import com.jonki.Entity.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class Validator {
    public boolean checkUsername(final String username) {
        return username.matches("[a-zA-Z0-9_.-]{6,36}");
    }

    public boolean checkEmail(final String email) {
        return email.length() > 1;
    }

    public boolean checkPassword(final String password) {
        return password.matches("^\\w{6,36}$");
    }

    public boolean checkReenterPassword(final String password, final String reenterPassword) {
        return password.equals(reenterPassword);
    }

    public boolean checkAvatar(final MultipartFile multipartFile) {
        return multipartFile.getOriginalFilename().endsWith(".jpg")
                || multipartFile.getOriginalFilename().endsWith(".png");
    }

    public List<Message> filterMessages(final String search, final List<Message> listMessages) {
        List<Message> listMessagesAfterFilter = new ArrayList<>();
        int count = 0;
        for (Message message : listMessages) {
            if (message.getSenderUser().getUsername().contains(search)
                    || message.getSubject().contains(search)
                    || message.getText().contains(search)) {
                listMessagesAfterFilter.add(listMessages.get(count));
            }
            ++count;
        }
        return listMessagesAfterFilter;
    }

    public List<Message> filterSentMessages(final String search, final List<Message> listMessages) {
        List<Message> listMessagesAfterFilter = new ArrayList<>();
        int count = 0;
        for (Message message : listMessages) {
            if (message.getRecipientUser().getUsername().contains(search)
                    || message.getSubject().contains(search)
                    || message.getText().contains(search)) {
                listMessagesAfterFilter.add(listMessages.get(count));
            }
            ++count;
        }
        return listMessagesAfterFilter;
    }

    public boolean isYourMessage(final Long id, final List<Message> listMessages) {
        for (Iterator<Message> it = listMessages.iterator(); it.hasNext(); ) {
            if (it.next().getId().equals(id))
                return true;
        }
        return false;
    }

    public boolean isYourFriend(final Long id, final List<Friendship> listFriends) {
        for (int i = 0; i < listFriends.size(); ++i) {
            if (listFriends.get(i).getFriendB().equals(id))
                return true;
        }

        return false;
    }

    public boolean isYourInvitation(final Long id, final List<Invitation> listYourInvitations) {
        for (int i = 0; i < listYourInvitations.size(); ++i) {
            if (listYourInvitations.get(i).getToID().equals(id))
                return true;
        }

        return false;
    }

    public boolean isInvitationForYou(final Long id, final List<Invitation> listInvitationsForYou) {
        for (int i = 0; i < listInvitationsForYou.size(); ++i) {
            if (listInvitationsForYou.get(i).getFromID().equals(id))
                return true;
        }

        return false;
    }
}