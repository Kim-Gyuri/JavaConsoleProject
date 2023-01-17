package console.LottoV4.main.java.model;

import console.LottoV4.main.java.util.SplitGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 로또 구매자 객체
public class Person {

    private List<Lotto> myLotto;        //구매한 로또
    private LottoMachine lottoMachine;

    public Person() {
        myLotto = new ArrayList<>();
        lottoMachine = new LottoMachine();
    }

    // 로또 구매하기 (money :지불한 금액, lottoNumbers :직접 입력하여 구매한 로또)
    public List<String> buyLotto(int money, List<String> lottoNumbers) {
        lottoMachine.inputMoney(money); //돈 넣고,

        lottoNumbers.forEach(this::buyLotto_manually); //직접 입력하여 구매한 로또
        while(lottoMachine.canLotto()) { //랜덤기계(lottoMachine)로 뽑은 로또
            buyLotto_Auto();
        }
        return getHistory(); // 구매한 로또를 출력해주기 위해 List<String>으로 반환함
    }

    // 직접 입력한 로또
    private void buyLotto_Auto() {
        myLotto.add(lottoMachine.getLottoNumbers_Auto());
    }

    // 랜덤 생성 로또
    private void buyLotto_manually(String numbers) {
        myLotto.add(lottoMachine.convertToLotto(numbers));
    }

    //구매한 로또를 -> String 으로 변환
    private List<String> getHistory() {
        List<String> history = new ArrayList<>();

        for(Lotto lotto :myLotto)
            history.add(lotto.showLotto());

        return history;
    }

    /*
     * 당첨되었는지 확인하는 메소드
     * checkLotto_version1(String winningNumbers, String bonusNumber) : 당첨 번호를 (당첨번호, 보너스 볼을 나누어) 직접 작성했을 경우
     * checkLotto_version2()                                          : 당첨 번호를 랜덤으로 뽑았을 경우
     * checkLotto_version3(String winningNumbers)                     : 당첨 번호를 ("당첨번호+보너스볼"을 1줄로) 직접 작성했을 경우
     */
    public Statistic checkLotto_version1(String winningNumbers, String bonusNumber) {
         Statistic statistic = new Statistic();
         List<String> winningNumber = Arrays.asList(SplitGenerator.splitWithSign(winningNumbers, " "));

         for (Lotto lotto : myLotto)
             statistic.pushStatistic(Rank.valueOf(lotto.getCorrectCount_version1(winningNumber), lotto.isContain_version1(bonusNumber)));

         return statistic;
    }


    public Statistic checkLotto_version2() {
        Draw draw = lottoMachine.getWinningNumbers_Auto();

        draw.showDraw();
        LottoNo bonusBall = draw.getBonusBall();
        List<LottoNo> drawBalls = draw.getDrawBalls();

        Statistic statistic = new Statistic();

        for (Lotto lotto : myLotto)
            statistic.pushStatistic(Rank.valueOf(lotto.getCorrectCount_version2(drawBalls), lotto.isContain_version2(bonusBall)));

        return statistic;
    }

    public Statistic checkLotto_version3(String winningNumbers) {
        Draw draw2 = lottoMachine.convertToDraw(winningNumbers);
        draw2.showDraw();
        LottoNo bonusBall2 = draw2.getBonusBall();
        List<LottoNo> drawBalls2 = draw2.getDrawBalls();

        Statistic statistic = new Statistic();

        for (Lotto lotto2 : myLotto)
            statistic.pushStatistic(Rank.valueOf(lotto2.getCorrectCount_version2(drawBalls2), lotto2.isContain_version2(bonusBall2)));

        return statistic;
    }
}
