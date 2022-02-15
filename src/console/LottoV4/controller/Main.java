package console.LottoV4.controller;


import console.LottoV4.domain.Board;
import console.LottoV4.domain.BoardDao;
import console.LottoV4.result.WinningLottoNumbers;
import console.LottoV4.view.print.OutputPrint;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        int count = 0;

        BoardDao boardDao = OutputPrint.printPurchasedLotto();
        WinningLottoNumbers winningLottoNumbers = OutputPrint.printPrizeLotto();
        //System.out.println(winningLottoNumbers.getBoard().getLottoTicketNumbers());
        //System.out.println(winningLottoNumbers.getBonusNumber());
       // checkLottoResult(boardDao, winningLottoNumbers);
        List<Board> list = boardDao.getList();
        for (Board board : list) {
            //System.out.println(board.getTickets());
            count = (int) list.stream()
                    .filter(purchaseOne -> board.getLottoTicketNumbers()
                            .contains(purchaseOne))
                    .count();
        }


        System.out.println("개수"+count);



    }

/*
    private static void checkLottoResult(BoardDao dao, WinningLottoNumbers winningLottoNumbers) {
        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers, dao);
        lottoComparator.getLottoResult(dao);
        OutputPrint.printResult(lottoComparator.getLottoResult(dao));
    }
*/


}
