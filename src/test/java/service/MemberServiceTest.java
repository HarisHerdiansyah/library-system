package service;

import exception.DataInvalidException;
import exception.DataNotFoundException;
import model.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import repository.MemberRepository;

@DisplayName("Member Service Test")
public class MemberServiceTest {
    private final MemberRepository repository = new MemberRepository();
    private final MemberService service = new MemberService(repository);

    @Test
    @DisplayName("Register success without any exception")
    public void registerTest() {
        assertDoesNotThrow(() -> {
            service.register("John Doe");
        });
    }

    @Test
    @DisplayName("When name.length() < 4, must throw invalid exception")
    public void registerTestNameLengthException() {
        assertThrows(DataInvalidException.class, () -> {
            service.register("Jay");
        });
    }

    @Test
    @DisplayName("When name isn't valid, must throw invalid exception")
    public void registerTestNameIsNotValid() {
        assertThrows(DataInvalidException.class, () -> {
            service.register("J@y 3m C4rl0 R|c0");
        });
        assertThrows(DataInvalidException.class, () -> {
            service.register("");
        });
        assertThrows(DataInvalidException.class, () -> {
            service.register("   ");
        });
    }

    @Test
    @DisplayName("When name.length() < 4 and not valid, must throw invalid exception")
    public void registerTestWithAbsoluteException() {
        assertThrows(DataInvalidException.class, () -> {
            service.register("J0y");
        });
    }

    @Test
    @DisplayName("Delete existing member with no exception")
    public void deleteWithNoException() {
        Member member = new Member("John Doe");
        repository.save(member);

        assertDoesNotThrow(() -> {
            service.deleteMember(member.getMemberId());
        });
    }

    @Test
    @DisplayName("Delete existing member with not found exception")
    public void deleteWithException() {
        assertThrows(DataNotFoundException.class, () -> {
            service.deleteMember("123");
        });
    }
}
