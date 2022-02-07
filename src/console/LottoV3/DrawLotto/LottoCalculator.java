package console.LottoV3.DrawLotto;

import console.LottoV2.Ball;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class LottoCalculator {
    private List<Ball> balls = new ArrayList<Ball>();
    private int cnt = 0;
    private int MAX_NUM = 45;

    public LottoCalculator() {
        for (int i=1; i<=MAX_NUM; i++) {
            Ball ball = new Ball();
            ball.setNumber(i);
            balls.add(ball);
        }
    }

    public void setBalls() {
        Collections.shuffle(balls);
    }

    public int getBall() {
        return balls.get(cnt++).getNumber();
    }

}
