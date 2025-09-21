package sn.bacomputer.security;

import java.security.SecureRandom;
import java.util.Random;


public class PasswordGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_";
    private static final int PASSWORD_LENGTH = 12; // Ajuster la longueur selon vos besoins
    private static final Random random = new SecureRandom();

    public static String generatePassword() {
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            password.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return password.toString();
    }
}
