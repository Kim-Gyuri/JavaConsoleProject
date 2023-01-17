package console.LottoV3.DrawLotto;

import console.LottoV2.LottoCalculator;

import java.util.ArrayList;
import java.util.List;

// 추첨 시스템
public class Draw {
    public static final int BONUS_NUMBER_INDEX = 6;
    public static List<Integer> savedBall; //추첨공 임시보관소

    //기본 생성자
    public Draw() {
        savedBall = new ArrayList<Integer>();
    }

    //로또 추첨 발표한다.
    public void post() {
        System.out.println("-------------------------------------");
        System.out.println("이번 달, 로또 추첨을 시작합니다.");
        System.out.println("-------------------------------------");

        System.out.println("볼을 꺼냅니다.");

        System.out.println();
        for (int i=0; i<=BONUS_NUMBER_INDEX; i++) {
            if (i<6) {
                System.out.println(i + 1 +"번째 볼은 " + savedBall.get(i) + "입니다.");
            } else {
                System.out.println("보너스 볼은 " + savedBall.get(i) + "입니다.");
            }
        }

        System.out.println("오늘의 당첨 번호는 " + savedBall.toString() + "입니다.");
        System.out.println("-------------------------------------");
    }

    //당첨볼을 뽑는다.
    public List<Integer> pickBall() {
        LottoCalculator lc = new LottoCalculator();
        lc.setBalls();

        for (int i=0; i<=BONUS_NUMBER_INDEX; i++) {
            savedBall.add(lc.getBall());
        }
        return savedBall;
    }
}
