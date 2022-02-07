package console.LottoV3;

import console.LottoV3.DrawLotto.LottoResult;
import console.LottoV3.LottoShop.LottoShop;

public class Main {
    public static void main(String[] args) {
        //1.로또를 구매한다.
        LottoShop shop = new LottoShop();
        shop.start();
        //2. 추첨을 시작한다.
        LottoResult lottoResult = new LottoResult();
        lottoResult.start();
        //3. 상금을 확인한다.
    }
}
