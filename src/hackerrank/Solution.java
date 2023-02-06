package hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;



class Bomb {
    int x;
    int y;

    Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

}

class Game {
    static int[][] bomb_grid;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean isActive = true;

    public static int[][] setGame(List<String> chars) {
        int rows = chars.size();
        int cols = chars.get(0).length();
        bomb_grid = new int[rows][cols];


        for (int i=0; i<rows; i++) {
            String s = chars.get(i);
            for (int j=0; j<cols; j++) {
                if (s.charAt(j) == 'O') {
                    bomb_grid[i][j] = 2;
                }
            }
        }
        return bomb_grid;
    }

    public static void bomberMan(int n, List<String> grid) {
        int[][] bomb_grid = setGame(grid);
        n--;
        n = checkTime(n);

        explode(n, bomb_grid);
        printResult(bomb_grid);
    }

    private static int checkTime(int n) {
        if (n > 0) {
            n = n % 4;
            if (n == 0) n = n + 4;
        }
        return n;
    }

    private static void explode(int n, int[][] bomb_grid) {
        int rows = bomb_grid.length;
        int cols = bomb_grid[0].length;

        while (n-- > 0) {
            if (isActive) {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        if (bomb_grid[i][j] > 0) {
                            bomb_grid[i][j]--;
                        } else {
                            bomb_grid[i][j] = 3;
                        }
                    }
                }
                isActive = false;
            } else {
                List<Bomb> bombList = new ArrayList<>();
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        if (bomb_grid[i][j] > 0) {
                            if (--bomb_grid[i][j] == 0) {
                                bombList.add(new Bomb(i, j));
                            }
                        }
                    }
                }

                while (bombList.size() > 0) {
                    Bomb bomb = bombList.remove(0);
                    for (int d=0; d<dx.length; d++) {
                        int temp_x = bomb.x + dx[d];
                        int temp_y = bomb.y + dy[d];
                        if (temp_x <0 || temp_x >= rows || temp_y < 0 || temp_y >= cols) {
                            continue;
                        }
                        bomb_grid[temp_x][temp_y] = 0;
                    }
                }
                isActive = false;
            }
        }
    }

    private static void printResult(int[][] bomb_grid) {
        int rows = bomb_grid.length;
        int cols = bomb_grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (bomb_grid[i][j] == 0) {
                    System.out.print('.');
                } else {
                    System.out.print('O');
                }
            }
            System.out.println();
        }
    }


}
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int r = Integer.parseInt(firstMultipleInput[0]);

        int c = Integer.parseInt(firstMultipleInput[1]);

        int n = Integer.parseInt(firstMultipleInput[2]);

        List<String> grid = IntStream.range(0, r).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .collect(toList());

        Game.bomberMan(n, grid);


        bufferedReader.close();

    }
}
