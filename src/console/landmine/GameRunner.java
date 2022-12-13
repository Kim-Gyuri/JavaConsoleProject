package console.landmine;

import java.util.Scanner;

public class GameRunner {
    public static int x,y;
    public static String ENTER_X = "x 좌표를 입력하세요.";
    public static String ENTER_Y = "y 좌표를 입력하세요.";
    public static int MINE_TOTAL = 16;
    public static String unknown = " ? ";
    public static String mine = " * ";
   public static Scanner sc = new Scanner(System.in);

    // 게임의 룰을 출력한다.
    public void init() {
        System.out.println("\n\n================ Welcome to Minesweeper ! ================\n");
        System.out.println("지뢰가 없는 모든 곳을 지우는 게임입니다.");
        System.out.println("선택한 위치의 좌표(x,y)를 입력하여 확인할 수 있습니다.");
        System.out.println("[단, 좌표범위값은 1~10]");
        System.out.println("16개의 지뢰가 있습니다. 지뢰가 있는 좌표를 선택하면 게임이 종료됩니다. \n");
    }

    // 게임 실행 메소드
    public void run() {

        //지뢰를 생성합니다.
        Game game = new Game();
        game.generateMines(MINE_TOTAL);
        game.update();

        // 1 turn 입력을 받는다.
        getNumber_X(sc, game);
        getNumber_Y(sc, game);

        // 처음 좌표를 선택했을 때, 플레이어가 패배하지 않도록
        // 만약 플레이어가 지뢰를 다른 tile 로 옮기면, 게임은 지뢰를 다른 tile 로 옮길 것이다.
        // 지뢰가 있는 타일을 선택한다.
        if (game.getTile(x,y).equals(mine) == true) {
            game.generateMines(1);
            game.FIELD[x][y] = unknown;
        }

        game.clear(x,y);
        game.detect();
        game.update();

        // 게임에서 패배하기 전까지 loop 반복한다.
        while(true) {
            if (game.getDone() == true && game.getWin() == true) { // 게임에서 승리했을 때
                System.out.println("게임에서 이기셨습니다.");
                game.onEnd();
                break;
            } else if (game.getDone() == true) { // 게임에서 패배했을 때
                game.onEnd();
                break;
            } else if (game.getDone() == false) { // 게임이 아직 진행 중인 경우,
                x = -1; //리셋
                y = -1;
                getNumber_X(sc, game); //다음 turn 좌표(x,y)를 입력 받는다.
                getNumber_Y(sc, game);
                game.turn(x,y);     //turn (x,y) 적용
                game.isVictory();  // 판단
                game.detect();
                game.update();
            }
        }
    }

    private void getNumber_X(Scanner sc, Game game) {
        System.out.print(ENTER_X);
        while (true) {
            y = sc.nextInt();
            if (game.isCorrectNum(y) == true) break;
            continue;
        }
    }

    private void getNumber_Y(Scanner sc, Game game) {
        System.out.println(ENTER_Y);
        while (true) {
            x = sc.nextInt();
            if (game.isCorrectNum(x) == true) break;
            continue;
        }
    }
}
