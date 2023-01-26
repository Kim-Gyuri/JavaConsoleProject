package console.ghostLeg.view;

import java.util.Scanner;

public class InputView {


    public static int numOfPlayers(Scanner scanner) {
        System.out.println("-------------------------------------");
        System.out.println("플레이하려는 인원이 몇명입니까?");
        System.out.println("-------------------------------------");
        return Integer.parseInt(scanner.nextLine());
    }

    public static String playerName(Scanner scanner) {
        System.out.println("-------------------------------------");
        System.out.println("이름을 입력해 주세요. [예:윌러 캐넌 오너]");
        System.out.println("-------------------------------------");
        return scanner.nextLine();
    }

    public static String thingsOfBottom(Scanner scanner) {
        System.out.println("-------------------------------------");
        System.out.println("각각의 라인별 결과를 입력해 주세요. [예: pass pass fail]");
        System.out.println("-------------------------------------");
        return scanner.nextLine();
    }

}
