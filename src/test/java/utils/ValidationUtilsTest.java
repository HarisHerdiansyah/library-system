package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Validation Utils Test")
public class ValidationUtilsTest {
    @Test
    @DisplayName("Valid Name Must be True")
    public void validateValidName() {
        String john = "John Doe";
        String adele = "Adele Kowalski";
        String risa = "Risa Oribe";

        assertTrue(ValidationUtils.validateName(john));
        assertTrue(ValidationUtils.validateName(adele));
        assertTrue(ValidationUtils.validateName(risa));
    }

    @Test
    @DisplayName("Name with Number Must be False")
    public void validateNameWithNumber() {
        String nameWithNumber1 = "john001";
        String nameWithNumber2 = "4dele K0walski";

        assertFalse(ValidationUtils.validateName(nameWithNumber1));
        assertFalse(ValidationUtils.validateName(nameWithNumber2));
    }

    @Test
    @DisplayName("Name with Special Characters Must be False")
    public void validateNameWithSpecChars() {
        String nameWithSpec1 = "john@@@";
        String nameWithSpec2 = "@dele Kowal$ki";

        assertFalse(ValidationUtils.validateName(nameWithSpec1));
        assertFalse(ValidationUtils.validateName(nameWithSpec2));
    }

    @Test
    @DisplayName("Name with No Letter Must be False")
    public void validateNameWithoutLetter() {
        String noLetter = "@#$%^&103470";
        assertFalse(ValidationUtils.validateName(noLetter));
    }

    @Test
    @DisplayName("Who's Gonna Name Their Child by Number?")
    public void validateNameButNumber() {
        String onlyNumber = "1234567890";
        assertFalse(ValidationUtils.validateName(onlyNumber));
    }

    @Test
    @DisplayName("Again, Who's Gonna Name Their Child by Special Characters?")
    public void validateNameButSpecialCharacters() {
        String onlySpecialCharacters = "!@#$%^&*&()<>?:{}|";
        assertFalse(ValidationUtils.validateName(onlySpecialCharacters));
    }

    @Test
    @DisplayName("Empty String Must be False")
    public void validateEmptyString() {
        String emptyString = "";
        assertFalse(ValidationUtils.validateName(emptyString));
    }

    @Test
    @DisplayName("One Space Must be False")
    public void validateOneWhitespace() {
        String oneSpace = " ";
        assertFalse(ValidationUtils.validateName(oneSpace));
    }

    @Test
    @DisplayName("More Than One Space Must be False")
    public void validateMoreThanOneSpace() {
        String multiSpace = "   ";
        assertFalse(ValidationUtils.validateName(multiSpace));
    }

    @Test
    @DisplayName("One Letter is Enough to be True")
    public void validateOneLetter() {
        String oneLetter = "N";
        assertTrue(ValidationUtils.validateName(oneLetter));
    }
}
