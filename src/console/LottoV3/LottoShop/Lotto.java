package console.LottoV3.LottoShop;

import java.util.ArrayList;

// 로또 객체
public class Lotto {
    private ArrayList<Integer> lotto; //6개의 번호를 담을 저장소

    //기본 생성자
    public Lotto(ArrayList<Integer> lotto) {
        this.lotto = lotto;
    }

    //getter
    public ArrayList<Integer> getLotto() {
        return lotto;
    }

    //setter
    public void setLotto(ArrayList<Integer> lotto) {
        this.lotto = lotto;
    }

    @Override
    public String toString() {
        String info = "";
        for (Integer a : lotto) {
            info += " " + a;
        }
        return info;
    }
}
