package console.LottoV3.DrawLotto;

import java.util.Arrays;

public class LottoResult {
    private int[] ball = new int[7]; //6볼+보너스볼 = 7

    public void start() {
        System.out.println("-------------------------------------");
        System.out.println("이번 달, 로또 추첨을 시작합니다.");
        System.out.println("-------------------------------------");
        LottoCalculator lc = new LottoCalculator();
        lc.setBalls();

        System.out.println("볼을 꺼냅니다.");
        for (int i=0; i<=6; i++) {
            ball[i] = lc.getBall();
            if (i<6) {
                System.out.println(i + 1 +"번째 볼은 " + ball[i] + "입니다.");
            } else {
                System.out.println("보너스 볼은 " + ball[6] + "입니다.");
            }
        }
        System.out.println();
        System.out.println("오늘의 당첨 번호는 " + Arrays.toString(ball) + "입니다.");
        System.out.println("-------------------------------------");
    }
}
