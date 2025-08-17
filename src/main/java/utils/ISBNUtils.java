package utils;

import java.security.SecureRandom;

public class ISBNUtils {
    private static final SecureRandom RAND = new SecureRandom();

    private static char checkISBN(String firstTwelveDigits) {
        if (firstTwelveDigits == null || firstTwelveDigits.length() != 12) {
            throw new IllegalArgumentException("Input must be at least 12 characters.");
        }

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int d = firstTwelveDigits.charAt(i) - '0';
            sum += (i % 2 == 0) ? d : (3 * d);
        }
        int check = (10 - (sum % 10)) % 10;
        return (char)('0' + check);
    }

    public static String generateISBN() {
        StringBuilder sb = new StringBuilder(12);
        sb.append("978");
        for (int i = 0; i < 9; i++) {
            sb.append(RAND.nextInt(10));
        }
        char check = checkISBN(sb.toString());
        return sb.append(check).toString();
    }
}
