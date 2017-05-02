package com.jonki.ServiceImpl;

import com.jonki.Service.RandomNumberService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomNumberServiceImpl implements RandomNumberService {

    @Override
    public int randomActivationCode() {
        return new Random().nextInt(8999) + 1000;
    }

    @Override
    public String randomNewPassword() {
        char[] alphabet = ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789").toCharArray();

        Random random = new Random();

        byte randomLength = (byte) (random.nextInt(14) + 6);
        byte randomElementNumber;
        String newPassword = "";

        for (int i = 0; i < randomLength; ++i) {
            randomElementNumber = (byte) random.nextInt(alphabet.length);

            newPassword = new StringBuilder(newPassword).append(alphabet[randomElementNumber]).toString();
        }

        return newPassword;
    }
}
