package console.ghostLeg.domain;

import console.ghostLeg.util.SplitGenerator;

public class GhostLegGenerator {

    public static final String[] SHAPE_ARR = {"│", "──", "┤", "├"};
    public static final int STANDARD_LINE = 1;
    public static final int BRIDGE_LINE = 3;
    public static final int FINAL_LINE = STANDARD_LINE + BRIDGE_LINE;
    public static final int HEIGHT= 8;

    public String[] setThingOfBottom(String things) {
        return SplitGenerator.splitWithSign(things, " ");
    }

    // 막대기 n개 긋기
    public String[][] createField(int numOfPlayers) {
        String[][] field = new String[HEIGHT][numOfPlayers * FINAL_LINE];

        setVerticalLines(numOfPlayers, field);
        setHorizontalLines(field);

        return field;
    }

    //세로 선
    private void setVerticalLines(int numOfPlayers, String[][] field) {
        for(int x = 0; x < HEIGHT; x++) {
            for(int y = 0; y < FINAL_LINE * numOfPlayers; y++) {

                if(y % 4 == STANDARD_LINE - 1) {
                    field[x][y] = SHAPE_ARR[0];
                } else {
                    field[x][y] = "  ";
                }
            }
        }
    }

    //가로 선
    private void setHorizontalLines(String[][] field) {
        int numOfLines = field[0].length / FINAL_LINE;
        int[] checkedLine = new int[numOfLines];

        for(int x = 1; x < field.length - 1; x++) {
            for(int y = 0; y < numOfLines - 1; y++) {

                if((int)(Math.random() * 2) == 0)
                    checkedLine[y] += checkedLine[y] < 3 ? DrawLine(field , x, y * FINAL_LINE) : 0;
            }

            if(x == field.length - 2)
                for(int i = 0; i < numOfLines - 1; i++) {
                    if(checkedLine[i] == 0) {
                        x = 1;
                        break;
                    }
                }
        }
    }

    private static int DrawLine(String[][] field, int x, int y) {

        if(field[x][y].equals(SHAPE_ARR[0]) && field[x][y + FINAL_LINE].equals(SHAPE_ARR[0])) {
            field[x][y] = SHAPE_ARR[3];

            for(int i = 1 ; i < FINAL_LINE + 1; i++) {
                if(i < FINAL_LINE) {
                    field[x][y + i] = SHAPE_ARR[1];
                } else {
                    field[x][y + i] = SHAPE_ARR[2];
                }
            }
            return 1;
        }
        return 0;
    }


}
