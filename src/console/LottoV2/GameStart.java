package console.LottoV2;

import java.util.Arrays;

public class GameStart {
    private int[] ball = new int[7];

    public void start() {
        System.out.println("로또 추첨을 시작합니다.");
        LottoCalculator lc = new LottoCalculator();
        lc.setBalls();

        System.out.println("볼을 꺼냅니다.");
        for (int i=0; i<=6; i++) {
            ball[i] = lc.getBall();
            if (i<6) {
                System.out.println(i + 1 + "번째 볼은 " + ball[i] + "입니다");
            } else {
                System.out.println("보너스 볼은 " + ball[6] + "입니다.");
            }

        }

        System.out.println("오늘의 당첨 번호는 " + Arrays.toString(ball) + "입니다.");


    }
}
