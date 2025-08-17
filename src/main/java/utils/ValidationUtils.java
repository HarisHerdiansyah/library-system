package utils;

public class ValidationUtils {
    public static boolean validateName(String name) {
        for (char n: name.toCharArray()) {
            if (!Character.isLetter(n) && !Character.isSpaceChar(n)) {
                return false;
            }
        }
        return true;
    }
}
