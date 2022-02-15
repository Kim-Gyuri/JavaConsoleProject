package console.LottoV4.result;

import console.LottoV4.domain.BoardDao;

import java.util.List;

public class LottoComparator {

    private LottoResult lottoResult;

    public LottoComparator(WinningLottoNumbers winningLottoNumbers, BoardDao dao) {
        this.lottoResult = new LottoResult(winningLottoNumbers, dao);

    }

    public LottoComparator() {

    }

    public List<Integer> getNum(WinningLottoNumbers winningLottoNumbers, BoardDao dao) {
        List<Integer> oneTicketNumberMatchedCount = lottoResult.getOneTicketNumberMatchedCount(dao, winningLottoNumbers);
        return oneTicketNumberMatchedCount;
    }

    public void getMatchedType(WinningLottoNumbers winningLottoNumbers, BoardDao dao) {
        lottoResult.applyOneTicketResult(dao,winningLottoNumbers);
    }

/*
    public LottoResult getLottoResult(BoardDao purchasedLottoTickets) {
     /*  for(Board purchasedOne : purchasedLottoTickets.getList()) {
           lottoResult.applyOneTicketResult(purchasedOne);
       }
       return lottoResult;
      */
       // lottoResult.applyOneTicketResult(purchasedLottoTickets);
     //   return lottoResult;
   // }

}
