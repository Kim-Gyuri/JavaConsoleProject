package console.LottoV4.main.java.model;

import java.util.Comparator;

// 로또 등수 객체
public enum Rank implements Comparator<Rank> {
    FIRST(6, 2000000000),
    SECOND(5, 30000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    MISS(0, 0);

    private int countOfMatch; //일치되는 숫자 개수
    private int winningMoney; //상금

    private Rank(int countOfMatch, int winningMoney) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public static Rank valueOf(int countOfMatch, boolean matchBonus){
        /*
         * 1등 6개 일치
         * 2등 5개 + 보너스번호 일치
         * 3등 5개 일치
         * 4등 4개 일치
         * 5등 3개 일치
        */
        if (countOfMatch == 6)
            return FIRST;
        if (countOfMatch == 5 && matchBonus == true)
            return SECOND;
        if (countOfMatch == 5 || (countOfMatch == 4 && matchBonus == true))
            return THIRD;
        if (countOfMatch == 4 || (countOfMatch == 3 && matchBonus == true))
            return FOURTH;
        if (countOfMatch == 3 || (countOfMatch == 2 && matchBonus == true))
            return FIFTH;
        return MISS;
    }

    @Override
    public int compare(Rank o1, Rank o2) {
        return o1.getWinningMoney() - o2.getWinningMoney();
    }
}
