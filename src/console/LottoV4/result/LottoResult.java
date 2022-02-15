package console.LottoV4.result;


import console.LottoV4.domain.Board;
import console.LottoV4.domain.BoardDao;
import console.LottoV4.winnigsType.WinningsType;

import java.util.*;

import static console.LottoV4.winnigsType.WinningsType.FIVE_AND_BONUS_MATCH;
import static console.LottoV4.winnigsType.WinningsType.FIVE_MATCH;

public class LottoResult {

    private static final int ZERO = 0;
    private static final int MIN_MATCH_NUMBER_COUNT_TO_GET_PRIZE = 3;
    private static final int FIVE_MATCHED = 5;
    private static final int ONE_COUNT = 1;

    private WinningLottoNumbers winningLottoNumbers;
    private Map<WinningsType, Integer> resultCounts;
    private int count;
    private BoardDao dao;
    private List<Integer> matchSize = new ArrayList<>();

    public LottoResult(WinningLottoNumbers winningLottoNumbers, BoardDao dao) { //,BoardDao dao
        this.winningLottoNumbers = winningLottoNumbers;
        this.dao = dao;
        this.resultCounts = new HashMap<>();
        initCount();
    }

    public LottoResult() {

    }

    private void initCount() {
        for (WinningsType winningsType : WinningsType.values()) {
            resultCounts.put(winningsType, ZERO);
        }
    }

    public void applyOneTicketResult(BoardDao purchased, WinningLottoNumbers winningLottoNumbers) {
        List<Integer> matchedCountList = getOneTicketNumberMatchedCount(purchased, winningLottoNumbers);

        for (Integer one : matchedCountList) {
            if (one < MIN_MATCH_NUMBER_COUNT_TO_GET_PRIZE) {
                System.out.println("실패");
                return;
            }
            increaseCountOfMatched(matchedCountList, purchased);
        }
    }

    private void increaseCountOfMatched(List<Integer> matchedCountList, BoardDao purchased) {
        System.out.println("값이 들어왔습니다. " + matchedCountList);
        matchedCountList.stream().forEach(WinningsType::getLottoMatchType);


        for (int i=0; i<matchedCountList.size(); i++) {
            if (matchedCountList.get(i) == FIVE_MATCHED) {
                System.out.println("다섯");
                handleFiveMatchType(purchased);
            }
            else {
                System.out.println("다른것");
                handleOtherMatchType(matchedCountList);
            }
        }

           // System.out.println("matchedCountList = " + matchedCountList);
         //   List<WinningsType> lottoMatchType = WinningsType.getLottoMatchType(one);
           // System.out.println("lottoMatchType.toString() = " + lottoMatchType.toString());

    }

    private void handleFiveMatchType(BoardDao purchasedTicket) {
        List<Board> list = purchasedTicket.getList();
        for(Board purchasedOne : list) {
         //   System.out.println("purchasedOne = " + purchasedOne.getLottoTicketNumbers());
          //  System.out.println("보너스 번호 확인" + winningLottoNumbers.getBonusNumber());
            if (purchasedOne.hasBonusNumber(winningLottoNumbers.getBonusNumber())) {
              //  System.out.println("win"+winningLottoNumbers.getBonusNumber());
                System.out.println("list toString" + purchasedOne.getLottoTicketNumbers() +"당첨금 50000000입니다.");
                Integer currentMatched = resultCounts.get(FIVE_AND_BONUS_MATCH);
                resultCounts.put(FIVE_AND_BONUS_MATCH, currentMatched + ONE_COUNT);
                return;
            }
            else {
                System.out.println("t상금 5천만원은 아니다.");
            }
            Integer currentMatched = resultCounts.get(FIVE_MATCH);
            resultCounts.put(FIVE_MATCH, currentMatched+ONE_COUNT);
            //System.out.println("다섯자리만 맞추셨습니다." + purchasedOne.getLottoTicketNumbers());
        }
    }

    private void handleOtherMatchType(List<Integer> matchedCountListss) {
      //  WinningsType winningsType = lottoMatchTypes.get(ZERO);
       // Integer currentMatched = resultCounts.get(winningsType);
       // System.out.println("currentMatched = " + currentMatched);
        //resultCounts.put(winningsType, currentMatched+ONE_COUNT);

    }

    //private
    public List<Integer> getOneTicketNumberMatchedCount(BoardDao purchased, WinningLottoNumbers winningLottoNumbers) {
        Board winBoard = winningLottoNumbers.getBoard();
        List<Board> list = purchased.getList();
        Iterator<Board> iterator = list.iterator();
        while(iterator.hasNext()) {
            Board next = iterator.next();
            count = (int) next.getLottoTicketNumbers()
                    .stream()
                    .filter(purchasedOne -> winBoard.getLottoTicketNumbers()
                           .contains(purchasedOne))
                    .count();
            System.out.println("count = " + count);
            matchSize.add(count);
        }
        System.out.println("총 count=" + matchSize.toString());
        return matchSize;
    }

}
