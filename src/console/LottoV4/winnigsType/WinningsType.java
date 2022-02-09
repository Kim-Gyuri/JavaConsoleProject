package console.LottoV4.winnigsType;
/*
로또 당첨금 규칙
1~5순서까지 동일 ,-->당첨금
-1등:6자리를 다 맞힘 	-->몇십업 ,%=26억
-2등:5자리+보너스 맞힘 -->4찬만원~5천만원, %=5천만원
-3등:5자리           -->100만원초반,%=135만원
-4등:4자리           -->5만원
-5등:3자리

 */
public enum  WinningsType {

    THREE_MATCH("5등"),
    FOUR_MATCH("4등"),
    FIVE_MATCH("3등"),
    FIVE_AND_BONUS_MATCH("2등"),
    SIX_MATCH("1등");

    private String value;

    WinningsType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }



}
