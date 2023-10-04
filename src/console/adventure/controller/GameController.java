package console.adventure.controller;

import console.adventure.domain.GameSystem;
import console.adventure.domain.player.Hero;

import java.util.Scanner;

import static console.adventure.resource.MenuNumbers.*;
import static console.adventure.view.InputView.*;
import static console.adventure.view.OutputView.*;


public class GameController {

    private static GameSystem system;
    private static Scanner scanner;


    public GameController() {
        system = new GameSystem();
        scanner = new Scanner(System.in);
    }

    public void startGame() throws InterruptedException {
        selectChampion();
        playGame();

    }

    public static void selectChampion() throws InterruptedException {
        boolean run = true;
        String selected = "";

        while (run) {
            system.logo();
            switch (getChampionSelection(scanner)) {
                // "전사" 챔피언을 선택할 것인지?
                case ONE:
                    displayChampionInfo(system, Hero.WARRIOR);
                    // "전사" 선택을 확정할 것인지?
                    if (confirmDecision()) {
                        selected += Hero.WARRIOR.getName();
                        run = false;
                    }
                    break;
                // "마법사" 챔피언을 선택할 것인지?
                case TWO:
                    displayChampionInfo(system, Hero.WIZARD);
                    // "마법사" 선택을 확정할 것인지?
                    if (confirmDecision()) {
                        selected += Hero.WIZARD.getName();
                        run = false;
                    }
                    break;
                // "궁수" 챔피언을 선택할 것인지?
                case THREE:
                    displayChampionInfo(system, Hero.ARCHER);
                    // "궁수" 선택을 확정할 것인지?
                    if (confirmDecision()) {
                        selected += Hero.ARCHER.getName();
                        run = false;
                    }
                    break;
                // 잘못된 입력 경우
                default:
                    displayInvalidCommand();
                    break;
            }
        }
        //선택한 챔피언 정보 출력
        system.chooseHero(selected);
        System.out.println(system.showPlayerInfo());
    }


    // 게임하기.
    public static void playGame() throws InterruptedException {
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
        system.guide(); // 게임 시나리오 출력
        Thread.sleep(3000);

        while (run) {
            //던전에 입장
            system.chooseEnemy();
            encounterMonster(system);

            //1. 몬스터와 마주침 [선택지]
            while (system.checkEnemyHP() && system.checkPlayerIsAlive()) {
                // 플레이어와 몬스터 HP/MP 정보보기
                player_enemy_status(system);

                String actionChoice = getActionSelection(scanner); // 선택지 선택
                switch (actionChoice) {
                    // 공격키 선택
                    case ONE:
                        String attackChoice = getAttackSelection(scanner);
                        play_caseONE(attackChoice, system);
                        break;
                    // 아이템 선택
                    case TWO:
                        String itemChoice = getItemSelection(scanner);
                        play_caseTWO(itemChoice, system);
                        break;
                    // 적에게서 도망가기 선택
                    case THREE:
                        play_caseTHREE(system);
                        if (!confirmDecision()) {
                            system.resetPlayer();
                            run = false;
                        }
                        break;
                    // 잘못된 입력키
                    default:
                        displayInvalidCommand();
                        Thread.sleep(1000);
                        break;
                }
            }
            // check player defeat
            if (!run) {
                displayGameOver();
                system.thanks();
                break;
            }
            //2. 몬스터를 물리친 경우 [선택지]
            system.checkDropRate_bounty(); //item drop

            if (!confirmDecision()) { // Do you want to play more?
                system.resetPlayer();
                system.thanks();
                break;
            }
        }
    }

    private static boolean confirmDecision() throws InterruptedException {
        String play_more = getPlayerDecision(scanner);
        while(!play_more.equals(ONE) && !play_more.equals(TWO)) {
            displayInvalidCommand();
            Thread.sleep(1000);
            play_more = getPlayerDecision(scanner);
        }
        return play_more.equals(ONE);
    }
}
