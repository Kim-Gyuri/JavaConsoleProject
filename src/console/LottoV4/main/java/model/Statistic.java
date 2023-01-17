package console.LottoV4.main.java.model;

import java.util.SortedMap;
import java.util.TreeMap;

// 구매자의 로또 등수를 계산해주는 객체
public class Statistic {

    private SortedMap<Rank, Integer> statistic; //Map "key: Rank" "value: 당첨된 개수"를 기록한다.

    public Statistic() {
        statistic = new TreeMap<>();
        statistic.put(Rank.MISS, 0);
        statistic.put(Rank.FIRST, 0);
        statistic.put(Rank.SECOND, 0);
        statistic.put(Rank.THIRD, 0);
        statistic.put(Rank.FOURTH, 0);
        statistic.put(Rank.FIFTH, 0);
    }

    // 등수별 당첨된 로또가 있는지 판단한다.
    public void pushStatistic(Rank rank) {
        Integer count = statistic.get(rank);
        count++;
        statistic.put(rank, count);
    }

    public SortedMap<Rank,Integer> getStatistic() {
        return statistic;
    }

}
