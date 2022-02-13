package console.LottoV4.controller;

import console.exam07.BoardDao;
import console.exam07.LottoComparator;
import console.exam07.OutputPrint;
import console.exam07.WinningLottoNumbers;

public class Main {
    public static void main(String[] args) {

        BoardDao boardDao = OutputPrint.printPurchasedLotto();
        WinningLottoNumbers winningLottoNumbers = OutputPrint.printPrizeLotto();
        //System.out.println(winningLottoNumbers.getBoard().getLottoTicketNumbers());
        //System.out.println(winningLottoNumbers.getBonusNumber());
        checkLottoResult(boardDao, winningLottoNumbers);

    }


    private static void checkLottoResult(BoardDao dao, WinningLottoNumbers winningLottoNumbers) {
        LottoComparator lottoComparator = new LottoComparator(winningLottoNumbers, dao);
        lottoComparator.getLottoResult(dao);
        OutputPrint.printResult(lottoComparator.getLottoResult(dao));
    }


}
