package repository;

import model.Member;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class MemberRepository {
    private final HashMap<String, Member> members = new HashMap<>();

    public MemberRepository() {}

    public Collection<Member> getAll() {
        return Collections.unmodifiableCollection(members.values());
    }

    public Member getById(String memberId) {
        return members.get(memberId);
    }

    public void save(Member member) {
        members.put(member.getMemberId(), member);
    }

    public void delete(String memberId) {
        members.remove(memberId);
    }
}
