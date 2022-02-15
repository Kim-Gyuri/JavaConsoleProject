package console.LottoV4.view.print;

import console.LottoV4.domain.Board;
import console.LottoV4.domain.BoardDao;
import console.LottoV4.domain.LottoNumber;
import console.LottoV4.domain.ticketgenerator.Generator;
import console.LottoV4.result.WinningLottoNumbers;
import console.LottoV4.view.InputView;
import console.LottoV4.winnigsType.WinningsType;

import java.util.List;

public class OutputPrint {
    private static final String NEW_LINE = "\n";

    public static BoardDao printPurchasedLotto() {
        int purchase = InputView.getPurchase();
        Generator generator = new Generator();
        BoardDao boardDao = generator.generateTickets(purchase);
        System.out.printf("%d개를 구매했습니다."+NEW_LINE, purchase);
        List<Board> list = boardDao.getList();
        for (Board board : list) {
            System.out.println(board.getTickets());
        }
        return boardDao;
    }

    public static WinningLottoNumbers printPrizeLotto() {
        WinningLottoNumbers winningLottoNumbers = InputView.getWinningLottoNumbers();
        Board board = winningLottoNumbers.getBoard();
        LottoNumber bonusNumber = winningLottoNumbers.getBonusNumber();
        System.out.println(NEW_LINE+"번호를 발표하겠습니다" + board.getLottoTicketNumbers());
        System.out.println("보너스 번호는요 [" + bonusNumber + "]");
        return winningLottoNumbers;
    }


    public static void printEachNumberMatchedCountMessage(WinningsType lottoMatchType,
                                                          int countOfMatchedNumbers) {
        System.out.printf(lottoMatchType.getPrizeMoney() + NEW_LINE, countOfMatchedNumbers);
    }
/*
    public static void printResult(LottoResult lottoResult) {
        for (WinningsType lottoMatchType : WinningsType.values()) {
            int countOfMatchedNumbersOfSpecificType = lottoResult.getCountOfMatchedNumbersOfSpecificType(lottoMatchType);
            System.out.println(countOfMatchedNumbersOfSpecificType);
        }

    }

 */
}
