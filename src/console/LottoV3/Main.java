package console.LottoV3;

import console.LottoV3.DrawLotto.LottoResult;
import console.LottoV3.LottoShop.LottoShop;

public class Main {
    public static void main(String[] args) {
        LottoShop shop = new LottoShop();
        shop.start();
        LottoResult lottoResult = new LottoResult();
        lottoResult.start();

    }
}
