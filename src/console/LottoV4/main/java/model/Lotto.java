package console.LottoV4.main.java.model;

import java.util.ArrayList;
import java.util.List;

//로또 객체
public class Lotto {

    private List<LottoNo> lottoNumbers;

    public Lotto(List<LottoNo> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public Lotto(String[] lottoNumbers) {
        this.lottoNumbers = new ArrayList<>();
        for (int i=0; i<lottoNumbers.length; i++)
            this.lottoNumbers.add(new LottoNo(Integer.parseInt(lottoNumbers[i])));
    }

    //로또 번호를 출력한다.
    public String showLotto() {
        List<String> lotto = new ArrayList<>();

        for (LottoNo lottoNo : lottoNumbers)
            lotto.add(lottoNo.getStringNumber());

        return lotto.toString();
    }


    /*
      * getCorrectCount() : 일치되는 숫자 개수 구하기
      * isContain() : 보너스볼이 있는지 확인한다.
    */
    public int getCorrectCount_version1(List<String> winningNumbers) {
        return (int) lottoNumbers.stream().filter((x) -> winningNumbers.contains(x.getStringNumber())).count();
    }

    public boolean isContain_version1(String stringNumber) {
        return (int) lottoNumbers.stream().filter(x -> x.getStringNumber().equals(stringNumber)).count()!=0;
    }

    public int getCorrectCount_version2(List<LottoNo> winningNumbers) {
        return (int) lottoNumbers.stream()
                .filter(num -> winningNumbers.stream().anyMatch(ball -> num.getStringNumber().equals(ball.getStringNumber()))).count();
    }

    public boolean isContain_version2(LottoNo bonusNumber) {
        return (int) lottoNumbers.stream().filter(x -> x.getStringNumber().equals(bonusNumber.getStringNumber())).count()!=0;
    }
}
