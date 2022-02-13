package console.LottoV4.winnigsType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
로또 당첨금 규칙
1~5순서까지 동일 ,-->당첨금
-1등:6자리를 다 맞힘 	-->몇십업 ,%=20억
-2등:5자리+보너스 맞힘 -->4찬만원~5천만원, %=5천만원
-3등:5자리           -->100만원초반,%=135만원
-4등:4자리           -->5만원
-5등:3자리

 */
public enum WinningsType {

    THREE_MATCH(3,5000, false),
    FOUR_MATCH(4, 50000, false),
    FIVE_MATCH(5, 1000000, false),
    FIVE_AND_BONUS_MATCH(5,50000000, true),
    SIX_MATCH(6,2000000000, false),
    MISS(0,0,false);

    private int countMatchedNumbers;
    private int prizeMoney;
    private boolean containBonusBall;

    WinningsType(int countMatchedNumbers, int prizeMoney, boolean containBonusBall) {
        this.countMatchedNumbers = countMatchedNumbers;
        this.prizeMoney = prizeMoney;
        this.containBonusBall = containBonusBall;
    }

    public int getCountMatchedNumbers() {
        return countMatchedNumbers;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public static List<WinningsType> getLottoMatchType(int countMatchedNumbers) {

        return Arrays.stream(WinningsType.values())
                .filter(
                        lottoMatchType -> lottoMatchType.getCountMatchedNumbers() == countMatchedNumbers )
                .collect(Collectors.toList());
    }



}
