package console.StudentManage;

import java.util.Scanner;
import java.util.Vector;

public class Manager {
    Scanner sc = new Scanner(System.in);
    Vector<Member> members = new Vector<Member>();
    public int selectMenu() {
        System.out.println("<학생 관리 프로그램> 다음 메뉴를 선택하세요.");
        System.out.println("[1]:추가 [2]:삭제 [3]:조회 [4]:목록 [0]:종료");
        int key = sc.nextInt();
        return key;
    }
    public void run() {
        int key = 0;
        while((key = selectMenu()) != 0) {
            switch(key) {
                case 1:
                    addMember();
                    break;
                case 2:
                    delMember();
                    break;
                case 3:
                    searchMember();
                    break;
                case 4:
                    listMember();
                    break;
                default:
                    System.out.println("잘 못 입력하였습니다.");
            }
        }
    }
    public void addMember() {
        System.out.println("번호를 입력하세요 : ");
        int stuNum = sc.nextInt();

        sc.nextLine();
        System.out.println("이름을 입력하세요 : ");
        String stuName = sc.nextLine();

        Member m = new Member(stuNum, stuName);
        members.add(m);
        System.out.println("정상적으로 추가되었습니다.");
    }
    public void delMember() {
        System.out.println("삭제할 번호를 입력하세요 : ");
        int stu = sc.nextInt();

        for(int i = 0; i < members.size(); i++) {
            if(stu == members.get(i).getNum()) {
                members.remove(i);
                System.out.println("삭제 되었습니다.");
            }
        }
    }
    public void searchMember() {
        System.out.println("검색할 학생 번호를 입력하세요 : ");
        int stu = sc.nextInt();

        for(int i = 0; i < members.size(); i++) {
            if(stu == members.get(i).getNum()) {
                System.out.println(members.get(i));
            }
        }
    }
    public void listMember() {
        for (int i = 0; i < members.size(); i++) {
            System.out.println(members.get(i));
        }
    }
}