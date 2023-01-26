package console.ghostLeg.view;

import console.ghostLeg.domain.GhostLegField;
import console.ghostLeg.domain.PlayerGroup;

import static console.ghostLeg.domain.GhostLegGenerator.FINAL_LINE;
import static console.ghostLeg.domain.GhostLegGenerator.SHAPE_ARR;

public class ResultView {

    public static void result(GhostLegField field, PlayerGroup players) {
        System.out.println("-------------------------------------");
        System.out.println("결과창");
        System.out.println("-------------------------------------");
        field.printField(players);
        printResult(field, players);
    }

    // 결과
    public static void printResult(GhostLegField ghostLegField, PlayerGroup players) {
        String tmp = "";

        String[][] field = ghostLegField.getField();
        String[] thingsOfBottom = ghostLegField.getThingsOfBottom();

        for(int q = 0 ; q < field[0].length ; q += FINAL_LINE) {
            int x = 0, y = q;

            for(int w = 0 ; w < field.length ; w++) {
                if(field[x][y].equals(SHAPE_ARR[3])) y += FINAL_LINE;
                else if(field[x][y].equals(SHAPE_ARR[2])) y -= FINAL_LINE;

                //사다리 타기
                tmp = field[x][y];
                field[x][y] = tmp;
                x++;

            }
            System.out.println(players.searchPlayer(q / FINAL_LINE) + "의 결과는 " + thingsOfBottom[y / FINAL_LINE]);

        }
    }
}
