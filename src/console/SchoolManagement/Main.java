package console.SchoolManagement;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Teacher lizzy = new Teacher(1, "Lizzy", 500);
        Teacher mellisa = new Teacher(2, "Mellisa", 700);
        Teacher john = new Teacher(3, "John", 600);

        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(lizzy);
        teacherList.add(mellisa);
        teacherList.add(john);

        Student joy = new Student(1, "joy", 4);
        Student monica = new Student(2, "monica", 12);
        Student chandler = new Student(3, "Chandler", 5);
        List<Student> studentList = new ArrayList<>();

        studentList.add(joy);
        studentList.add(monica);
        studentList.add(chandler);

        School ghs = new School(teacherList, studentList);

        Teacher megan = new Teacher(6, "Megan", 900);

        ghs.addTeacher(megan);

        joy.payFees(5000);
        monica.payFees(6000);
        System.out.println("GHS has earned $" + ghs.getTotalMoneyEarned());

        System.out.println("----------making school pay salary ---------------");
        lizzy.receiveSalary(lizzy.getSalary());
        System.out.println("GHS has spent for salary to " + lizzy.getName() + " and now has $" + ghs.getTotalMoneyEarned());

        john.receiveSalary(john.getSalary());
        System.out.println("GHS has spent for salary to " + john.getName() + " and now has $" + ghs.getTotalMoneyEarned());

        System.out.println(monica);
        mellisa.receiveSalary(mellisa.getSalary());
        System.out.println(mellisa);
    }
}
