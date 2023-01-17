package console.LottoV2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 로또 당첨 볼 뽑는 생성기계
public class LottoCalculator {
    private List<Ball> balls = new ArrayList<>();
    private int cnt = 0;
    private int MAX_NUM = 45;

    // 45 범위 내의 Ball 세팅한다.
    public LottoCalculator() {
        for (int i=1; i<=MAX_NUM; i++) {
            Ball ball = new Ball();
            ball.setNumber(i);
            balls.add(ball);
        }
    }

    public void setBalls() {
        Collections.shuffle(balls);
    }  // Ball 순서를 섞어준다.

    public int getBall() {
        return balls.get(cnt++).getNumber();
    }

}
