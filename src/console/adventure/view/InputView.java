package console.adventure.view;

import java.util.Scanner;

public class InputView {

    // 플레이 챔피언 선택지
    public static String getChampionSelection(Scanner scanner) {
        System.out.println("    ============================================================================");
        System.out.println("                  (1) Warrior        (2) Wizard        (3) Archer ");
        System.out.println("    ============================================================================");
        System.out.print("               select> ");
        return scanner.nextLine();
    }

    // (플레이어에게 결정을 묻는다. -> 하고 싶은지? 싫은지?)
    public static String getPlayerDecision(Scanner scanner) {
        System.out.println("    ============================================================================");
        System.out.println("         Do you want to play?                    (1) YES      (2) NO ");
        System.out.println("    ============================================================================");
        System.out.print("               select> ");
        return scanner.nextLine();
        //return scanner.nextLine().equals(ONE);
    }

    // 플레이 선택지
    public static String getActionSelection(Scanner scanner) {
        System.out.println("    ============================================================================");
        System.out.println("                  (1) Attack        (2) Drink health potion        (3) Run ");
        System.out.println("    ============================================================================");
        System.out.print("               select> ");
        return scanner.nextLine();
    }

    // 공격키 선택지
    public static String getAttackSelection(Scanner scanner) {
        System.out.println("    ============================================================================");
        System.out.println("                  (1) Basic Attack        (2) Ultimate        ");
        System.out.println("    ============================================================================");
        System.out.print("               select> ");
        return scanner.nextLine();
    }

    // 아이템 선택지
    public static String getItemSelection(Scanner scanner) {
        System.out.println("    ============================================================================");
        System.out.println("                  (1) Potion        (2) tears of phoenix        ");
        System.out.println("    ============================================================================");
        System.out.print("               select> ");
        return scanner.nextLine();
    }

    // 마주친 적을 물리친 후, 게임 진행할 것인지 묻는다.
    public static String getContinueOptions(Scanner scanner) {
        System.out.println("    ============================================================================");
        System.out.println("         What would you like to do now?");
        System.out.println("              (1) continue fighting        (2) Exit dungeon");
        System.out.println("    ============================================================================");
        System.out.print("               select> ");
        return scanner.nextLine();
    }
}
