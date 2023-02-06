package console.adventure.view;

import console.adventure.domain.GameSystem;
import console.adventure.domain.enemy.Enemy;
import console.adventure.domain.player.Hero;
import console.adventure.domain.player.Player;

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
                    system.getImage(Hero.WARRIOR.getName());
                    System.out.println(Hero.WARRIOR.toString());
                    if (chooseHero(scanner)) {
                        selected += Hero.WARRIOR.getName();
                        run = false;
                    }
                    break;
                case TWO:
                    system.getImage(Hero.WIZARD.getName());
                    System.out.println(Hero.WIZARD.toString());
                    if (chooseHero(scanner)) {
                        selected += Hero.WIZARD.getName();
                        run = false;
                    }
                    break;
                case THREE:
                    system.getImage(Hero.ARCHER.getName());
                    System.out.println(Hero.ARCHER.toString());
                    if (chooseHero(scanner)) {
                        selected += Hero.ARCHER.getName();
                        run = false;
                    }
                    break;
                default:
                    continue;
            }
        }
        system.chooseHero(selected);
        System.out.println(system.userInfo().toString());
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
        Player player = system.userInfo();
        system.guide();
        Thread.sleep(3000);
        GAME:
        while (run) {
            //던전에 입장
            Enemy enemy = system.chooseEnemy();
            encounterMonster(enemy,system);
            System.out.println(LINE);
            Thread.sleep(2400);

            //1. 몬스터와 마주침 [선택지]
            while (enemy.getHealthPoint() > ZERO) {
                system.getImage(player.getOccupation().getName(), enemy.getName());
                System.out.println(LINE_TYPE2);
                system.checkStatusWindow(player, enemy);

                switch (actionMenu(scanner)) {
                    case ONE:
                        play_caseONE(scanner, player, enemy, system);
                        break;

                    case TWO:
                        play_caseTWO(scanner, player);
                        break;

                    case THREE:
                        player.run_away(enemy);
                        Thread.sleep(1000);
                        continue GAME;

                    default:
                        continue;
                }
            }
            // check player defeat
            if (player.getHealthPoint() < ONE_POINT) {
                System.out.println("      You limp out of the dungeon, weak from battle");
                break;
            }
            //2. 몬스터를 물리친 경우 [선택지]
            system.checkDropRate_bounty(player, enemy); //item drop?
            if (go_on(scanner,system)) break; // Do you want to play more?
        }

    }

    private static boolean go_on(Scanner scanner,GameSystem system) {
        System.out.println("    ============================================================================");
        System.out.println("         What would you like to do now?");
        System.out.println("              (1) continue fighting        (2) Exit dungeon");
        System.out.println("    ============================================================================");
        System.out.print("               select> ");
        String play_more = scanner.nextLine();
        while(!play_more.equals(ONE) && !play_more.equals(TWO)) {
            System.out.println("               Invalid command");
            System.out.print("               select> ");
            play_more = scanner.nextLine();
        }
        if (play_more.equals(TWO)) {
            system.thanks();
            return true;
        }
        return false;
    }

    private static void play_caseONE(Scanner scanner, Player player, Enemy enemy, GameSystem system) throws InterruptedException {
        String select = attackMenu(scanner);
        if (select.equals(ONE)) {
            system.getImage(player.getName(), enemy.getName(), BASIC_ATTACK);
            System.out.println(LINE);
            player.basicAttack(player, enemy);
            System.out.println(LINE);
            Thread.sleep(2000);
            if (player.getHealthPoint() < ONE_POINT) {
                System.out.println("      You have taken too much damage, you are too weak to go on");
                Thread.sleep(1000);
                return;
            }
        } else if (select.equals(TWO)) {
            system.getImage(player.getName(), enemy.getName(), ULTIMATE);
            System.out.println(LINE);
            player.ultimate(player, enemy);
            System.out.println(LINE);
            Thread.sleep(2000);
            if (player.getHealthPoint() < ONE_POINT) {
                System.out.println("      You have taken too much damage, you are too weak to go on");
                Thread.sleep(1000);
                return;
            }
        } else {
            System.out.println("               Invalid command!, try again.");
            System.out.print("               select> ");
        }
    }

    private static void play_caseTWO(Scanner scanner, Player player) throws InterruptedException {
        String select2 = drinkMenu(scanner);
        if (select2.equals(ONE)) {
            player.drinkPotion();
            Thread.sleep(1000);
        } else if (select2.equals(TWO)) {
            player.useTearsOfPhoenix();
            Thread.sleep(1000);
        } else {
            System.out.println("               Invalid command!, try again.");
            System.out.print("               select> ");
        }
    }

    private static void encounterMonster(Enemy enemy, GameSystem system) {
        system.getImage(enemy.getName());
        System.out.println("      " + enemy.getName()+ " has appeared!!!");
        System.out.println(enemy.toString());
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
