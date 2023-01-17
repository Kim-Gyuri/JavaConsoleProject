package console.LottoV3.Winnings;

import console.LottoV3.DrawLotto.Draw;
import console.LottoV3.LottoShop.Lotto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Winnings {
    public static final int BONUS_NUMBER_INDEX = 6;
    public static final int THE_SIXTH = 6;
    public static final int THE_FIVE = 5;
    public static final int THE_FOUR = 4;
    public static final int THE_THREE = 3;

    private Iterator iterator;

    public Winnings() { }


    //당첨 결과를 확인한다.
    public void result(ArrayList<Lotto> lotto, List<Integer> winnings) {
        Integer bonusBall = winnings.get(BONUS_NUMBER_INDEX);
        winnings.remove(BONUS_NUMBER_INDEX);
        iterator = lotto.iterator();
        int i = 1;
        while (iterator.hasNext()) {
            Lotto temp = (Lotto) iterator.next();
            ArrayList<Integer> lottoTemp = temp.getLotto();
            lottoTemp.retainAll(winnings);
            String result = rank(lottoTemp, bonusBall);
            System.out.println("-------------------------------------");
            System.out.println(i+"회 게임 결과는 " + result + "\t");
            System.out.println("일치 번호 ->" + lottoTemp);
            System.out.println("-------------------------------------");
            i++;
        }
    }

    //등수를 확인한다.
    private String rank(ArrayList<Integer> temp, int bonusBall) {
        Draw draw = new Draw();
        /* 1등 6개 일치
        2등 5개 + 보너스번호 일치
        3등 5개 일치
        4등 4개 일치
        5등 3개 일치
        */
        int count = temp.size();

        if (count == THE_SIXTH) {
            return "1등";
        } else if (count == THE_FIVE && temp.contains(bonusBall)){
            return "2등";
        } else if (count == THE_FIVE) {
            return "3등";
        } else if (count == THE_FOUR) {
            return "4등";
        } else if (count == THE_THREE) {
            return "5등";
        } else {
            return "꽝";
        }
    }
}
