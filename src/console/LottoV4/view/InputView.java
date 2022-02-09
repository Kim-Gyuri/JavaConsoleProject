package console.LottoV4.view;

import console.LottoV4.domain.Buyer;
import console.LottoV4.view.print.InputPrint;

import java.util.Scanner;

public class InputView {
    private static final Scanner sc = new Scanner(System.in);
    private static final String NUMBER_EXPRESSION = "\\d+";
    public static final String LOTTO_NUMBER_DELIMITER = ", ";
    private static final int ZERO = 0;
    private static final int MAX_RANGE = 10;

    private InputView() {}

    public static Buyer purchase() {
        InputPrint.printPurchaseInputMessage();
        String purchaseInput = sc.nextLine();
        validateNumber(purchaseInput);
        validateRange(purchaseInput);
        int input = Integer.parseInt(purchaseInput);
        return new Buyer(input);
    }


    private static int validateNumber(String numberOfTickets) {
        if (!numberOfTickets.matches(NUMBER_EXPRESSION)) {
            throw new IllegalArgumentException("자연수를 입력해주세요 (최대 10장 구매가능)");
        }
        return Integer.parseInt(numberOfTickets);
    }

    private static int validateRange(String numberOfTickets) {
        int number = Integer.parseInt(numberOfTickets);
        if (number< ZERO || number>MAX_RANGE) {
            throw new IllegalArgumentException("최대 10장 구매 가능합니다.");
        }
        return number;
    }
}
