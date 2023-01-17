package console.LottoV4.main.java.view;

import console.LottoV4.main.java.model.Rank;
import console.LottoV4.main.java.model.Statistic;

import java.util.List;
import java.util.Map;

public class ResultView {

    public static void lottoHistoryView(List<String> lottoHistory) {
        System.out.println("-------------------------------------");
        System.out.println("총 " + lottoHistory.size() + "개의 로또가 정상구매완료 되었습니다");
        System.out.println("-------------------------------------");
        for(String lotto : lottoHistory)
            System.out.println(lotto);
        System.out.println();
    }

    public static void lottoResultView(Statistic statistic) {
        System.out.println("-------------------------------------");
        System.out.println("당첨 통계");
        System.out.println("-------------------------------------");
        lottoStatisticView(statistic);
        lottoPercentView(statistic);
    }

    public static void lottoStatisticView(Statistic statistic) {
        for (Map.Entry<Rank, Integer> elem : statistic.getStatistic().entrySet())
            System.out.printf("%d개 일치 (%d원) - %d개\n", elem.getKey().getCountOfMatch(), elem.getKey().getWinningMoney(), elem.getValue());
    }

    public static void lottoPercentView(Statistic statistic) {
        int totalCount = 0;
        int totalWinningMoney = 0;

        for (Map.Entry<Rank, Integer> elem : statistic.getStatistic().entrySet()) {
            totalCount += elem.getValue();
            totalWinningMoney += elem.getKey().getWinningMoney() * elem.getValue();
        }

        System.out.println("총 수익은 " + totalWinningMoney + "(원)입니다.");
        System.out.print("총 수익률은 ");
        System.out.print((float) (totalCount-statistic.getStatistic().get(Rank.MISS))/totalCount);
        System.out.println("%입니다.");
    }
}
