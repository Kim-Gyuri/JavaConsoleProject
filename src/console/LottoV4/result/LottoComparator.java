package console.LottoV4.result;

import console.LottoV4.domain.BoardDao;

public class LottoComparator {

    private LottoResult lottoResult;

    public LottoComparator(WinningLottoNumbers winningLottoNumbers, BoardDao dao) {
        this.lottoResult = new LottoResult(winningLottoNumbers, dao);

    }

    public LottoComparator() {

    }


    public LottoResult getLottoResult(BoardDao purchasedLottoTickets) {
     /*  for(Board purchasedOne : purchasedLottoTickets.getList()) {
           lottoResult.applyOneTicketResult(purchasedOne);
       }
       return lottoResult;
      */
        lottoResult.applyOneTicketResult(purchasedLottoTickets);
        return lottoResult;
    }
}
