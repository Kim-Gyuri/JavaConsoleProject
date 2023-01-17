package console.LottoV4.main.java.controller;

import console.LottoV4.main.java.model.Person;
import console.LottoV4.main.java.view.InputView;
import console.LottoV4.main.java.view.ResultView;

import java.util.Scanner;

public class LottoMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Person purchaser = new Person();
        ResultView.lottoHistoryView(purchaser.buyLotto(InputView.totalPrice(scanner), InputView.getLottoNumber_manually(scanner)));
        /*
         * 당첨 번호를 입력하는 방식에 따른 결과 확인하는 방법...
         */
        //ResultView.lottoResultView(purchaser.checkLotto_version2());
          ResultView.lottoResultView(purchaser.checkLotto_version3(InputView.winningNumber(scanner)));
      //  ResultView.lottoResultView(purchaser.checkLotto_version1(InputView.winningNumber(scanner), InputView.bonusNumber(scanner)));
    }
}
