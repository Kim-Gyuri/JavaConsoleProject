package console.LottoV3;

import console.LottoV3.DrawLotto.Draw;
import console.LottoV3.LottoShop.Lotto;
import console.LottoV3.LottoShop.LottoShop;
import console.LottoV3.Winnings.Winnings;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //1.로또를 구매한다.
        //내가 구매한 로또를 확인한다.
        LottoShop shop = new LottoShop();
        ArrayList<Lotto> lottos = shop.sell();
        shop.printLotto();

        //2. 추첨을 시작한다.
        Draw lottoResult = new Draw();
        List<Integer> drawBall = lottoResult.pickBall();
        lottoResult.post();

        //3. 상금을 확인한다.
        Winnings bank = new Winnings();
        bank.result(lottos, drawBall);

    }
}
