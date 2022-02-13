package console.LottoV4.view;

import console.LottoV4.domain.ticketgenerator.Generator;
import console.LottoV4.result.WinningLottoNumbers;

import java.util.Scanner;

public class InputView {
    private static final Scanner sc = new Scanner(System.in);

    private InputView() {}

    public static int getPurchase() {
        System.out.println("몇 개를 구매하겠습니까?");
        int input = sc.nextInt();
        return input;
    }

    public static WinningLottoNumbers getWinningLottoNumbers() {
        Generator generator = new Generator();
        WinningLottoNumbers winningLottoNumbers = generator.generateWinningLotto();
        return winningLottoNumbers;
    }

}
