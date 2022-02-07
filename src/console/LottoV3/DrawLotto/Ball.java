package console.LottoV3.DrawLotto;
/*
public class Ball {
    private int number;
    public Ball() {}
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
}
*/

import java.util.ArrayList;

public class Ball {
    private ArrayList<Integer> ball; //6개의 번호를 담을 저장소

    //기본 생성자
    public Ball(ArrayList<Integer> ball) {
        this.ball = ball;
    }

    //getter
    public ArrayList<Integer> getBall() {
        return ball;
    }

    //setter
    public void setBall(ArrayList<Integer> ball) {
        this.ball = ball;
    }

    @Override
    public String toString() {
        String info ="";
        for (Integer b: ball) {
            info += "" + b;
        }
        return info;
    }
}
