package console.LottoV4.result;


import console.LottoV4.domain.Board;
import console.LottoV4.domain.BoardDao;
import console.LottoV4.winnigsType.WinningsType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static console.LottoV4.winnigsType.WinningsType.FIVE_AND_BONUS_MATCH;
import static console.LottoV4.winnigsType.WinningsType.FIVE_MATCH;

public class LottoResult {

    private static final int ZERO = 0;
    private static final int MIN_MATCH_NUMBER_COUNT_TO_GET_PRIZE = 3;
    private static final int FIVE_MATCHED_SIZE = 2;
    private static final int ONE_COUNT = 1;

    private WinningLottoNumbers winningLottoNumbers;
    private Map<WinningsType, Integer> resultCounts;
    private int count;
    private BoardDao dao;

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

    public void applyOneTicketResult(BoardDao purchasedOne) {
        int countMatched = getOneTicketNumberMatchedCount(purchasedOne);
        if (countMatched < MIN_MATCH_NUMBER_COUNT_TO_GET_PRIZE) {
            return;
        }
        increaseCountOfMatched(countMatched, purchasedOne);
    }

    private int getOneTicketNumberMatchedCount(BoardDao purchased) {

        Board winningLottoTicket = winningLottoNumbers.getBoard();
        List<Board> list = purchased.getList();

        for(Board purchasedOne : list) {
            count = (int) purchasedOne.getLottoTicketNumbers()
                    .stream()
                    .filter(purchasedLottoNumber -> winningLottoTicket.getLottoTicketNumbers()
                            .contains(purchasedLottoNumber))
                    .count();

        }
        return count;
    }

    private void increaseCountOfMatched(int countMatchedNumbers, BoardDao board) {
        List<WinningsType> lottoMatchType = WinningsType.getLottoMatchType(countMatchedNumbers);
        if (lottoMatchType.size() == FIVE_MATCHED_SIZE) {
            handleFiveMatchType(board);
            return;
        }
        handleOtherMatchType(lottoMatchType);
    }

    private void handleFiveMatchType(BoardDao purchasedTicket) {
        List<Board> list = purchasedTicket.getList();
        for(Board purchasedOne : list) {
            if (purchasedOne.hasBonusNumber(winningLottoNumbers.getBonusNumber())) {

                Integer currentMatched = resultCounts.get(FIVE_AND_BONUS_MATCH);
                resultCounts.put(FIVE_AND_BONUS_MATCH, currentMatched+ONE_COUNT);
                return;
            }
            Integer currentMatched = resultCounts.get(FIVE_MATCH);
            resultCounts.put(FIVE_MATCH, currentMatched+ONE_COUNT);
        }

    }

    private void handleOtherMatchType(List<WinningsType> lottoMatchTypes) {
        WinningsType winningsType = lottoMatchTypes.get(ZERO);
        Integer currentMatched = resultCounts.get(winningsType);
        resultCounts.put(winningsType, currentMatched+ONE_COUNT);
    }

    public int getCountOfMatchedNumbersOfSpecificType(WinningsType lottoMatchType) {
        return resultCounts.get(lottoMatchType);
    }

}
