package console.LottoV4.main.java.model;

import java.util.ArrayList;
import java.util.List;

public class Draw {
    private static final int BONUS_NUMBER_INDEX = 6;
    private static final String LINE = "-------------------------------------";
    private static final String POST = "이번 주 당첨번호를 확인하세요.";

    private List<LottoNo> drawBalls;

    public Draw(List<LottoNo> drawBalls) {
        this.drawBalls = drawBalls;
    }

    public Draw(String[] Numbers) {
        this.drawBalls = new ArrayList<>();
        for (int i=0; i<Numbers.length; i++)
            this.drawBalls.add(new LottoNo(Integer.parseInt(Numbers[i])));
    }

    public void showDraw() {
        List<String> draws = convertToString();
        System.out.println(LINE);
        System.out.println(POST);
        System.out.println(draws.toString());
    }

    public List<LottoNo> getDrawBalls() {
        drawBalls.remove(BONUS_NUMBER_INDEX);
        return drawBalls;
    }

    public LottoNo getBonusBall() {
        return drawBalls.get(BONUS_NUMBER_INDEX);
    }

    public List<String> convertToString() {
        List<String> draws = new ArrayList<>();

        for (LottoNo lottoNo : drawBalls)
            draws.add(lottoNo.getStringNumber());

        return  draws;
    }

}
