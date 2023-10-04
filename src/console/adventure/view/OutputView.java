package console.adventure.view;

import console.adventure.domain.GameSystem;
import console.adventure.domain.player.Hero;

import static console.adventure.resource.MenuNumbers.*;

public class OutputView {
    private static final String LINE =  "   ========================================================================================================================================================";
    private static final String LINE_TYPE2 =  "    ============================================================================";

    // 챔피언 정보 출력
    public static void displayChampionInfo(GameSystem system, Hero hero) {
        system.getHeroImage(hero.getName());
        System.out.println(hero.toString());
    }

    // 잘못된 입력에 대한 메시지
    public static void displayInvalidCommand() {
        System.out.println("               Invalid command!, try again.");
    }

    // 게임 오버 메시지
    public static void displayGameOver() {
        System.out.println("      You limp out of the dungeon, weak from battle");
        System.out.println("      Game Over!!!");
        System.out.println(LINE);
    }

    // 게임 진행 - 적을 마주침
    public static void encounterMonster(GameSystem system) throws InterruptedException {
        system.getEnemyImage();
        System.out.println("      " + system.enemyName() + " has appeared!!!");
        System.out.println(system.showEnemyInfo());
        System.out.println(LINE);
        Thread.sleep(2400);
    }

    // 플레이어, 적 HP/MP 상태창 정보
    public static void player_enemy_status(GameSystem system) {
        system.getHeroAndEnemyImage();
        System.out.println(LINE_TYPE2);
        system.checkStatusWindow();
    }

    // 공격했을 때 발생하는 이벤트 출력
    public static void play_caseONE(String select, GameSystem system) throws InterruptedException {

        if (select.equals(ONE)) {
            caseONE_basicAttack(system);
        } else if (select.equals(TWO)) {
            caseONE_Ultimate(system);
        } else {
            displayInvalidCommand();
            Thread.sleep(1000);
        }
    }

    // (공격키 - 기본공격) 모션처리
    private static void caseONE_basicAttack(GameSystem system) throws InterruptedException {
        System.out.println(LINE);
        system.basicAttack();
        System.out.println(LINE);
        Thread.sleep(2000);
    }

    // (공격키 궁극기) 모션처리
    public static void caseONE_Ultimate(GameSystem system) throws InterruptedException {
        System.out.println(LINE);
        system.ultimate();//MP가 남았는지 확인하여, 궁극기를 사용할 수 있는지 판단한다.
        System.out.println(LINE);
        Thread.sleep(2000);
    }

    // 아이템 사용시 이벤트 출력
    public static void play_caseTWO(String select, GameSystem system) throws InterruptedException {

        if (select.equals(ONE)) {
            system.drinkPotion();
            Thread.sleep(1000);
        } else if (select.equals(TWO)) {
            system.useTearsOfPhoenix();
            Thread.sleep(1000);
        } else {
            displayInvalidCommand();
            System.out.print("               select> ");
        }
    }

    // 적에게서 도망 선택시 이벤트 출력
    public static void play_caseTHREE(GameSystem system) throws InterruptedException {
        system.run_away();
        Thread.sleep(1000);
        return;
    }

}