package console.landmine;

/* --- 이 클래스에 논리 메소드를 포함시킨다. ---
     (해당 게임은 "콘솔 게임" 이다.)
* 1  12*12 2D Array 선언해서 사용한다.
* 2  처음 입장하면, "?"로 채워진 지뢰게임판이 출력된다.
* 3  first turn 경우, 선택한 좌표(x,y) 주위에 있는 3*3 좌표의 표시를 볼 수 있다. (만약 지뢰가 있다면 "?"로 알려준다.)
* 4  다음 turn 부터는, 선택한 좌표(x,y)의 표시값을 볼 수 있다.
* 5  게임이 끝났을 때, "승리/패배" 경우에 따라 메시지가 출력된다.
* 6  게임이 끝났을 때, 선택한 좌표의 표시값과 전체 지뢰게임판을 모두 확인할 수 있다.
 */

public class Game {
    public String[][] FIELD = new String[12][12];          // 지뢰가 배치된 지뢰게임판
    public String[][] FIELD_VISIBLE = new String[12][12]; //플레이어에게 보여줄 지뢰게임판
    public boolean isDone = false;
    public boolean isWin = false;
    public int X_COUNT = 10;
    public int Y_COUNT = 10;
    private static String unKnown = " ? ";   //처음 보여질 tile 표시
    private static String mine = " * ";     //지뢰 표시
    private String empty_type1 = "   ";    //아무것도 없음 표시
    private String empty_type2 = "\t   "; //가로 인덱스 표시를 위한 공백칸
    private String empty_type3 = " ";    //지뢰 수 표시를 포멧 -> empty_type3 + mine_count + empty_type3;
    private static int row = 0;

    //생성자, 처음 보여질 tiles 에 빈 공간을 채워준다.
    public Game(){
        for(int x = 0; x < FIELD.length; x++) {
            for(int y = 0; y < FIELD[row].length; y++){
                // buffer zones  공백 채우기
                if((x == 0 || x == FIELD.length - 1)||(y == 0 || y == FIELD[row].length - 1)){
                    FIELD[x][y] = empty_type1;
                    FIELD_VISIBLE[x][y] = empty_type1;
                }
                // ? 표시를 채워준다.
                else{
                    FIELD[x][y] = unKnown;
                    FIELD_VISIBLE[x][y] = unKnown;
                }
            }
        }
    }

    //게임을 시작할 때 생성된 field 를 보여준다.
    public void printGame(String[][] str) {
        System.out.print(empty_type2);  //--가로 인덱스 (1~10)
        for (int i=1; i<str.length-1; i++) {
            System.out.print(i + "   ");
        }
        System.out.println();           //-------------------


        // field 출력한다.
        for (int x=1; x<str.length-1; x++) {
              if (x<10) System.out.print(empty_type3 + x); //---세로 인덱스 (1~10)
              if (x==10) System.out.print(x);        //------
            for (int y=0; y<str[row].length; y++) {
                if (y>0 && y<str[row].length) System.out.print("|"); //---10x10 값 채우기
                System.out.print(str[x][y]);                      //------------
            }
            System.out.print("| \n"); // 10x10 오른쪽 | 벽 세우기)
        }
    }

    // 지뢰를 n개 생성합니다.
    public void generateMines(int n) {
        for (int m=0; m<n; m++) {
            //루프: 지뢰를 배치합니다.
            while(true) {
                // 난수를 사용하여 지뢰 위치할 공간을 생성한다.
                int x = (int) (X_COUNT*Math.random());
                int y = (int) (Y_COUNT*Math.random());

                if (x>=1 && x<=X_COUNT) {
                    if (y>=1 && y<=Y_COUNT) {
                        //판단, 현재 공간이 폭탄이 설치된 곳인지?
                        if (!FIELD[x][y].equals(mine)) { //폭탄이 설치된 곳이 아니라면 폭탄을 설치한다.
                            FIELD[x][y] = mine;
                            break;
                        }
                    }
                }
            }
        }
    }

    //처음 이동할 때, 선택한 공간 주위의 영역을 지웁니다.
    public void clear(int x, int y) {
        for (int i=(x-1); i<=(x+1); i++) {
            for (int j=(y-1); j<=(y+1); j++) {
                if (FIELD[i][j].equals(unKnown) == true) {
                    FIELD_VISIBLE[i][j] = empty_type1;
                    FIELD[i][j] = empty_type1;
                }
            }
        }
    }

    //공간의 정보를 가져온다.
    public String getTile(int x, int y) {
        return FIELD[x][y];
    }

    // 선택한 공간 주위의 지뢰 수를 탐색한다.
    public void detect() {
        //플레이어에게 보여줄 지뢰게임판 cycles
        for (int x=1; x<FIELD_VISIBLE.length-2; x++) {
            for (int y=1; y<FIELD_VISIBLE.length-2; y++) {
                if (FIELD[x][y].equals(empty_type1) == true) { //폭탄이 아니라면 인접한 위치에 폭탄이 몇개 있는지 확인한다.
                    int mine_count = 0;  //폭탄의 수를 저장할 변수
                    // 현재 위치 A를 중심으로 총 8방향의 위치에 폭탄이 있는지 확인한다.

                    for (int i=(x-1); i<=(x+1); i++) {  //폭탄이 있는 경우
                        for (int j=(y-1); j<=(y+1); j++) {
                            if (FIELD[i][j].equals(mine) == true) mine_count++;
                        }
                    }
                    FIELD_VISIBLE[x][y] = empty_type3 + mine_count + empty_type3;
                }
            }
        }
    }

    //입력한 좌표값 범위가 알맞는지 판단한다.
    public boolean isCorrectNum(int x) {
        if (x<0 || 10<x) {
            System.out.println("잘못 입력하셨습니다. [좌표 범위 : 1~10] ");
            return false;
        } else {
            return true;
        }
    }

    //플레이어가 선택한 공간 좌표를 가져와서 지뢰게임판을 조정한다.
    public void turn(int x, int y) {
        if (FIELD[x][y].equals(unKnown) == true) { //선택되지 않은 좌표인 경우, 삭제한다.
            isDone = false;
            FIELD_VISIBLE[x][y] = empty_type1;
            FIELD[x][y] = empty_type1;
        } else if (FIELD[x][y].equals(mine) == true) { // 지뢰 좌표를 선택한 경우,
            isDone = true;                            // -> 게임이 끝난다.
            isWin = false;                           //  -> 플레이어는 게임에서 패배.
            System.out.println("게임에서 패배하셨습니다.");
        } else if (FIELD_VISIBLE[x][y].equals(empty_type1) == true && FIELD[x][y].equals(empty_type1)) { //이미 지운 좌표인 경우
            isDone = false;
            System.out.println("지뢰를 모두 피했습니다!");
        }
    }

    // 플레이어가 승리했는지 판단, 지뢰가 아닌 안전한 좌표를 모두 지웠는지?
    public void isVictory() {
        int tile = 0; // 지우지 못한 safe tile 좌표 수
        for (int i=0; i<FIELD.length; i++) {
            for (int j=0; j<FIELD[row].length; j++) {
                if (FIELD[i][j].equals(unKnown) == true) tile++; //지우지 못한 tile 이라면, tile 카운팅한다.
            }
        }
        if (tile != 0) //아직 지우지 못한 safe tile 좌표가 남았다면, "아직 승리못한 상태"
            isWin = false;
        else {         // safe tile 를 모두 지운 경우, "플레이어 승리"
            isWin = true;
            isDone = true;
        }
    }

    public boolean getDone() { return isDone; } // 게임이 끝났는지 판단
    public boolean getWin() { return isWin; }  // 플레이어가 승리했는지 판단
    public void onEnd() { printGame(FIELD); } // 게임 종료 시 지뢰 위치를 모두 보여준다.

    // 1턴 지날 때마다 게임판을 업데이트해서 출력해준다.
    public void update() {
        printGame(FIELD_VISIBLE);
        System.out.println();
    }
}