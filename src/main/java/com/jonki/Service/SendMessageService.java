package com.jonki.Service;

public interface SendMessageService {
    public boolean send(final String recipientEmail,
                        final String subject,
                        final String textMessage);
}
