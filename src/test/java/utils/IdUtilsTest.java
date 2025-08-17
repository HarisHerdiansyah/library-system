package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ID Utils Test")
public class IdUtilsTest {
    @Test
    @DisplayName("Generate 10 Digit ID")
    public void generateTenDigit() {
        String id = IdUtils.generateId();
        assertEquals(10, id.length());
    }
}
