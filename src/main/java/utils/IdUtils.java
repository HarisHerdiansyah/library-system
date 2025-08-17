package utils;

import java.security.SecureRandom;

public class IdUtils {
    private static final SecureRandom RAND = new SecureRandom();
    private static final int ID_LENGTH = 10;

    public static String generateId() {
        StringBuilder sb = new StringBuilder(ID_LENGTH);
        sb.append(RAND.nextInt(9) + 1); // digit pertama 1–9
        for (int i = 1; i < ID_LENGTH; i++) {
            sb.append(RAND.nextInt(10)); // 0–9
        }
        return sb.toString();
    }
}
