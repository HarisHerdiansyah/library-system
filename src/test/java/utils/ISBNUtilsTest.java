package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ISBN Utils Test")
public class ISBNUtilsTest {
    @Test
    @DisplayName("ISBN With No Empty String")
    public void generateISBNWithNoEmptyString() {
        String ISBN = ISBNUtils.generateISBN();
        assertFalse(ISBN.isEmpty());
    }
}
