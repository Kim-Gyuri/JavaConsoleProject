package console.LottoV4.view;

import console.LottoV4.domain.Buyer;
import console.LottoV4.domain.LottoTicket;
import console.LottoV4.result.LottoResult;
import console.LottoV4.view.print.InputPrint;
import console.LottoV4.view.print.OutputPrint;
import console.LottoV4.winnigsType.WinningsType;

import java.util.List;

public class OutputView {

    public static void printPurchasedLottoTickets(Buyer buyer) {
        List<LottoTicket> lottoTicketList = buyer.getTicket();
        OutputPrint.printPaymentMessage(lottoTicketList.size());
        for (LottoTicket lottoTicket : lottoTicketList) {
            OutputPrint.printPurchasedLotto(lottoTicket);
        }
        InputPrint.printNewLine();
    }

    public static void printResult(LottoResult lottoResult) {
        OutputPrint.printResultMessage();
        for (WinningsType matchType : WinningsType.values()) {
            int countOfMatchedNumber =  lottoResult.getCountOfMatchedNumber(matchType);
        }
    }
}
