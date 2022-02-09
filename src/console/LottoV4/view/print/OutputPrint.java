package console.LottoV4.view.print;

import console.LottoV4.domain.LottoTicket;
import console.LottoV4.winnigsType.WinningsType;

import java.util.stream.Collectors;

import static console.LottoV4.view.InputView.LOTTO_NUMBER_DELIMITER;

public class OutputPrint {
    private static final String START_BRACKET = "[";
    private static final String END_BRACKET = "[";
    private static final String NEW_LINE = "\n";

    public static void printPaymentMessage(int numberOfTickets) {
        System.out.printf("%d개를 구매했습니다." + NEW_LINE, numberOfTickets);
    }

    public static void printPurchasedLotto(LottoTicket lottoTicket) {
        System.out.println(START_BRACKET +
                           lottoTicket.getLottoTicketNum().stream()
                                   .map(ball -> Integer.toString(ball.getNumber()))
                                   .collect(Collectors.joining(LOTTO_NUMBER_DELIMITER))
                           + END_BRACKET
                          );
    }

    public static void printResultMessage() {
        System.out.println("당첨 통계");
        System.out.println("-------------------------------------");
    }

    public static void printMatchedCountMessage(WinningsType matchType, int countOfMatchedNumber)  {
        System.out.println(matchType.getValue() + countOfMatchedNumber);
    }
}
