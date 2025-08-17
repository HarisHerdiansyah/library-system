package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Member Model Test")
public class MemberTest {
    @Test
    @DisplayName("Create Model")
    public void create() {
        Member member = new Member("New Member");
        assertEquals("New Member", member.getName());
    }
}
