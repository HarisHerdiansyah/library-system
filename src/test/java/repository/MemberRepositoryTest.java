package repository;

import model.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Member Repository Test")
public class MemberRepositoryTest {
    MemberRepository repository = new MemberRepository();

    @Test
    @DisplayName("Get All Member with 0 Result")
    public void getAllNull() {
        assertEquals(0, repository.getAll().size());
    }

    @Test
    @DisplayName("Add One Member")
    public void addOneMember() {
        Member member = new Member("Member 1");
        repository.save(member);
        assertEquals(1, repository.getAll().size());
    }

    @Test
    @DisplayName("Get One Member and Null")
    public void getOneNull() {
        assertNull(repository.getById("123"));
    }

    @Test
    @DisplayName("Get One Member and Not Null")
    public void getOneNotNull() {
        Member member = new Member("Member 2");
        repository.save(member);
        assertNotNull(repository.getById(member.getMemberId()));
    }

    @Test
    @DisplayName("Delete One Member")
    public void deleteOneMember() {
        Member member = new Member("Member 3");
        repository.save(member);
        repository.delete(member.getMemberId());
        assertEquals(0, repository.getAll().size());
    }
}
