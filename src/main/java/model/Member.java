package model;

import utils.IdUtils;

public class Member {
    private final String memberId;
    private String name;

    public Member(String name) {
        this.memberId = IdUtils.generateId();
        this.name = name;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId='" + memberId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
