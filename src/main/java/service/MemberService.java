package service;

import exception.DataInvalidException;
import exception.DataNotFoundException;
import model.Member;
import repository.MemberRepository;
import utils.ValidationUtils;

import java.util.Collection;

public class MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public void register(String name) throws DataInvalidException {
        if (name.length() < 4 || !ValidationUtils.validateName(name)) {
            throw new DataInvalidException("Name must be at least 4 characters and only letters");
        }

        Member member = new Member(name);
        repository.save(member);
    }

    public void deleteMember(String memberId) throws DataNotFoundException {
        Member member = repository.getById(memberId);
        if (member == null) {
            throw new DataNotFoundException("Member with Id: " + memberId + " not found.");
        }
        repository.delete(member.getMemberId());
    }

    public Collection<Member> getAllMember() {
        return repository.getAll();
    }
}
