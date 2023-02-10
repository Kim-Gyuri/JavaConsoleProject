package console.adventure.view;

import console.adventure.domain.GameSystem;
import console.adventure.domain.player.Hero;

import java.util.Scanner;

public class View {
    private static final String BASIC_ATTACK = "basic_attack";
    private static final String ULTIMATE = "ultimate";
    private static final String ONE = "1";
    private static final String TWO = "2";
    private static final String THREE = "3";
    private static final int ZERO = 0;
    private static final int ONE_POINT = 1;
    private static final String LINE =  "   ========================================================================================================================================================";
    private static final String LINE_TYPE2 =  "    ============================================================================";

    // 플레이어 선택
    public static void playerCharacter(Scanner scanner, GameSystem system) {
        boolean run = true;
        String selected = "";

        while (run) {
            system.logo();
            switch (mainMenu(scanner)) {
                case ONE:
                    system.getHeroImage(Hero.WARRIOR.getName());
                    System.out.println(Hero.WARRIOR.toString());
                    if (chooseHero(scanner)) {
                        selected += Hero.WARRIOR.getName();
                        run = false;
                    }
                    break;
                case TWO:
                    system.getHeroImage(Hero.WIZARD.getName());
                    System.out.println(Hero.WIZARD.toString());
                    if (chooseHero(scanner)) {
                        selected += Hero.WIZARD.getName();
                        run = false;
                    }
                    break;
                case THREE:
                    system.getHeroImage(Hero.ARCHER.getName());
                    System.out.println(Hero.ARCHER.toString());
                    if (chooseHero(scanner)) {
                        selected += Hero.ARCHER.getName();
                        run = false;
                    }
                    break;
                default:
                    System.out.println("               Invalid command!, try again.");
                    continue;
            }
        }
        system.chooseHero(selected);
        System.out.println(system.showPlayerInfo());
    }

    // 게임하기.
    public static void play(Scanner scanner, GameSystem system) throws InterruptedException {
        /*
        [던전]----------------------------------------------------------------
        1. 몬스터와 마주침
        [1] 싸우고 싶은지? ---> 공격 선택지 [1`-1 :평타] / [1`-2 :궁극기]
        [2] 아이템(포션/불사조눈물) 사용하고 싶은지? ---> 아이템 사용 선택지 [2`-1 :포션 사용] [2`-2 :불사조눈물 사용]
        [3] 도망간다.

        # [3] 선택 시 ---> 도망을 가다가 다른 몬스터와 마주칠 수 있다.
        # [1] 계속 하다가 플레이어 체력이 바닥난 경우 ---> 플레이어 죽음으로 게임 종료.
        # [1] 계속 하다가 몬스터를 물리치면 ---> 아이템 / 현상금을 획들 할 수 있다.

        2. 몬스터를 물리친 경우 선택지
        [1] 계속 던전에 있을 것인지? ---> 다시 '1. 몬스터와 마주침' 으로 돌아가 반복된다.
        [2] 게임 종료
        ----------------------------------------------------------------------
         */
        boolean run = true;
        system.guide();
        Thread.sleep(3000);

        GAME:
        while (run) {
            //던전에 입장
            system.chooseEnemy();
            encounterMonster(system);

            //1. 몬스터와 마주침 [선택지]
            while (system.checkEnemyHP() && system.checkPlayerIsAlive()) {
                // 플레이어와 몬스터 HP/MP 정보보기
                player_enemy_status(system);

                switch (actionMenu(scanner)) {
                    case ONE:
                        play_caseONE(scanner, system);
                        break;

                    case TWO:
                        play_caseTWO(scanner, system);
                        break;

                    case THREE:
                        play_caseTHREE(system);
                        continue GAME;

                    default:
                        System.out.println("               Invalid command!, try again.");
                        Thread.sleep(1000);
                        continue;
                }
            }
            // check player defeat
            if (system.checkPlayerHP()) {
                gameOver();
                break;
            }
            //2. 몬스터를 물리친 경우 [선택지]
            system.checkDropRate_bounty(); //item drop?
            if (go_on(scanner,system)) break; // Do you want to play more?
        }

    }

    private static void encounterMonster(GameSystem system) throws InterruptedException {
        system.getEnemyImage();
        System.out.println("      " + system.enemyName() + " has appeared!!!");
        System.out.println(system.showEnemyInfo());
        System.out.println(LINE);
        Thread.sleep(2400);
    }

    private static void player_enemy_status(GameSystem system) {
        system.getHeroAndEnemyImage();
        System.out.println(LINE_TYPE2);
        system.checkStatusWindow();
    }

    private static void play_caseONE(Scanner scanner, GameSystem system) throws InterruptedException {
        String select = attackMenu(scanner);
        if (select.equals(ONE)) {
            caseONE_basicAttack(system);
        } else if (select.equals(TWO)) {
            caseONE_Ultimate(system);
        } else {
            System.out.println("               Invalid command!, try again.");
            Thread.sleep(1000);
        }
    }

    private static void caseONE_basicAttack(GameSystem system) throws InterruptedException {
        System.out.println(LINE);
        system.basicAttack();
        System.out.println(LINE);
        Thread.sleep(2000);
    }

    private static void caseONE_Ultimate(GameSystem system) throws InterruptedException {
        System.out.println(LINE);
        system.ultimate();//MP가 남았는지 확인하여, 궁극기를 사용할 수 있는지 판단한다.
        System.out.println(LINE);
        Thread.sleep(2000);
    }

    private static void play_caseTWO(Scanner scanner, GameSystem system) throws InterruptedException {
        String select2 = drinkMenu(scanner);
        if (select2.equals(ONE)) {
            system.drinkPotion();
            Thread.sleep(1000);
        } else if (select2.equals(TWO)) {
            system.useTearsOfPhoenix();
            Thread.sleep(1000);
        } else {
            System.out.println("               Invalid command!, try again.");
            System.out.print("               select> ");
        }
    }

    private static void play_caseTHREE(GameSystem system) throws InterruptedException {
        system.run_away();
        Thread.sleep(1000);
        return;
    }

    private static void gameOver() {
        System.out.println("      You limp out of the dungeon, weak from battle");
        System.out.println("      Game Over!!!");
        System.out.println(LINE);
    }

    private static boolean go_on(Scanner scanner,GameSystem system) throws InterruptedException {
        String play_more = go_one_menu(scanner);
        while(!play_more.equals(ONE) && !play_more.equals(TWO)) {
            System.out.println("               Invalid command");
            Thread.sleep(1000);
            play_more = go_one_menu(scanner);
        }
        if (play_more.equals(TWO)) {
            system.thanks();
            return true;
        }
        return false;
    }

    private static String go_one_menu(Scanner scanner) {
        System.out.println("    ============================================================================");
        System.out.println("         What would you like to do now?");
        System.out.println("              (1) continue fighting        (2) Exit dungeon");
        System.out.println("    ============================================================================");
        System.out.print("               select> ");
        return scanner.nextLine();
    }


    private static String attackMenu(Scanner scanner) {
        System.out.println("    ============================================================================");
        System.out.println("                  (1) Basic Attack        (2) Ultimate        ");
        System.out.println("    ============================================================================");
        System.out.print("               select> ");
        return scanner.nextLine();
    }

    private static String drinkMenu(Scanner scanner) {
        System.out.println("    ============================================================================");
        System.out.println("                  (1) Potion        (2) tears of phoenix        ");
        System.out.println("    ============================================================================");
        System.out.print("               select> ");
        return scanner.nextLine();
    }

    private static String actionMenu(Scanner scanner) {
        System.out.println("    ============================================================================");
        System.out.println("                  (1) Attack        (2) Drink health potion        (3) Run ");
        System.out.println("    ============================================================================");
        System.out.print("               select> ");
        return scanner.nextLine();
    }

    private static boolean chooseHero(Scanner scanner) {
       System.out.println("    ============================================================================");
       System.out.println("         Do you want to play?                    (1) YES      (2) NO ");
       System.out.println("    ============================================================================");
       System.out.print("               select> ");
      return scanner.nextLine().equals(ONE);
   }

    private static String mainMenu(Scanner scanner) {
        System.out.println("    ============================================================================");
        System.out.println("                  (1) Warrior        (2) Wizard        (3) Archer ");
        System.out.println("    ============================================================================");
        System.out.print("               select> ");
        return scanner.nextLine();
    }

}
