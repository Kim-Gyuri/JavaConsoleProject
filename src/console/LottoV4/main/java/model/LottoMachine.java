package console.LottoV4.main.java.model;

import console.LottoV4.main.java.util.SplitGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// (로또 번호, 로또 당첨 볼) 생성기계
public class LottoMachine {

    private static int LOTTO_NUMBER_RANGE = 45; // 1~45 범위 내 번호
    private static int LOTTO_NUMBER_COUNT = 6;  // 로또는 6개의 숫자를 가진다.
    private static int LOTTO_DRAW_COUNT = 7;   // 당첨 볼은 총 7개이다.
    private static int LOTTO_PRICE = 1000;    // 로또 1장당 1000(원).

    private int tryCount = 0;  // 로또 뽑는 횟수

    // 자동으로 로또 번호를 생성한다.
    public Lotto getLottoNumbers_Auto() {
        decreaseCount();
        return new Lotto(getRandomLottoNumbers(getNumbersInRange(LOTTO_NUMBER_RANGE)));
    }

    // 자동으로 당첨번호를 생성한다.
    public Draw getWinningNumbers_Auto() {
        return new Draw(getRandomDrawNumbers(getNumbersInRange(LOTTO_NUMBER_RANGE)));
    }

    // 직접 입력해서 로또 번호를 생성한다.
    public Lotto convertToLotto(String numbers) {
        decreaseCount(); //충천한 횟수(tryCount)만큼 로또 생성한다.
        return new Lotto(SplitGenerator.splitWithSign(numbers," "));
    }

    // 직접 입력해서 당첨 번호를 생성한다.
    public Draw convertToDraw(String numbers) {
        return new Draw(SplitGenerator.splitWithSign(numbers, " "));
    }

    // 로또 뽑는 횟수를 충전한다.
    public void inputMoney(int money) {
        tryCount += money/LOTTO_PRICE;
    }

    public boolean canLotto() {
        return tryCount>0;
    }

    // 로또 뽑기 횟수 절감
    private void decreaseCount() {
        if (canLotto() == false)
            throw new RuntimeException("돈이 부족합니다.");
        tryCount--;
    }

    // 로또 번호(range :45까지)를 설정한다.
    private List<LottoNo> getNumbersInRange(int range) {
        List<LottoNo> numbersInRange = new ArrayList<>();

        for (int i=1; i<=range; i++)
            numbersInRange.add(new LottoNo(i));

        return numbersInRange;
    }

    // getLottoNumber_Auto() <--- 랜덤 숫자 뽑기 : shuffle()로 섞어 6개를 뽑는다.
    private List<LottoNo> getRandomLottoNumbers(List<LottoNo> numbersInRange) {
        Collections.shuffle(numbersInRange);
        return numbersInRange.subList(0, LOTTO_NUMBER_COUNT);
    }

    // getWinningNumber_Auto() <--- 랜덤 숫자 뽑기 : shuffle()로 섞어 7개를 뽑는다.
    private List<LottoNo> getRandomDrawNumbers(List<LottoNo> numbersInRange) {
        Collections.shuffle(numbersInRange);
        return numbersInRange.subList(0, LOTTO_DRAW_COUNT);
    }


}
