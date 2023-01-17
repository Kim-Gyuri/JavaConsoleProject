package console.LottoV4.main.java.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    public static int totalPrice(Scanner scanner) {
        System.out.println("-------------------------------------");
        System.out.println("구입금액을 입력해 주세요. (1장당에 1000(원)입니다.)");
        System.out.println("-------------------------------------");
        return Integer.parseInt(scanner.nextLine());
    }

    public static String winningNumber(Scanner scanner) {
        System.out.println("-------------------------------------");
        System.out.println("이번 주 당첨번호를 입력해 주세요.");
        System.out.println("-------------------------------------");
        return scanner.nextLine();
    }


    public static String bonusNumber(Scanner scanner) {
        System.out.println("-------------------------------------");
        System.out.println("보너스 볼을 입력해 주세요");
        System.out.println("-------------------------------------");
        return scanner.nextLine();
    }


    public static List<String> getLottoNumber_manually(Scanner scanner) {
        List<String> totalLottoNumber = new ArrayList<>();
        int count = howManyYouBuy_manually(scanner);

        if (count == 0 ) {
            return totalLottoNumber;
        } else {
            System.out.println("-------------------------------------");
            System.out.println("로또 번호를 작성하세요. (\"1~45\" 범위내의 숫자를 입력해야 합니다.)");
            System.out.println("[예시] 10 20 30 40 45 9");
            System.out.println("-------------------------------------");

            for (int i=0; i<count; i++)
                totalLottoNumber.add(scanner.nextLine());
        }

        return totalLottoNumber;
    }

    public static int howManyYouBuy_manually(Scanner scanner) {
        System.out.println("-------------------------------------");
        System.out.println("수동으로 구매할 로또 수를 입력하세요.");
        System.out.println("-------------------------------------");

        return Integer.parseInt(scanner.nextLine());
    }
}
