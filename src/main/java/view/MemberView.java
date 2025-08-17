package view;

import exception.DataInvalidException;
import exception.DataNotFoundException;
import model.Member;
import service.MemberService;

import java.util.Scanner;

public class MemberView {
    private final Scanner scanner = new Scanner(System.in);
    private final MemberService service;

    public MemberView(MemberService service) {
        this.service = service;
    }

    public void registerView() {
        System.out.print("Insert name: ");
        String memberName = scanner.nextLine();

        try {
            service.register(memberName);
            System.out.println("Register Member Success.");
        } catch (DataInvalidException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteMemberView() {
        System.out.print("Insert Member ID: ");
        String memberId = scanner.nextLine();

        try {
            service.deleteMember(memberId);
            System.out.println("Delete Member Success.");
        } catch (DataNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printAll() {
        System.out.println("Members:");
        for (Member m: service.getAllMember()) {
            System.out.println(m.toString());
        }
    }
}
