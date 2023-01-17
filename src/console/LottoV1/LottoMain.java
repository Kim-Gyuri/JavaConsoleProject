package console.LottoV1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

//로또
class Lotto{
    private Scanner sc = new Scanner(System.in);
    public void start() {
        System.out.println("구입할 로또 개수를 입력하시오");
        int getCnt = Integer.parseInt(sc.nextLine());

        for (int i=1; i<=getCnt; i++) {
            System.out.println(i+"번째 로또 번호" + lottoNum());
        }
    }
    //로직: 랜덤으로 판매할 로또를 만든다.
    public String lottoNum() {
        Random random = new Random();
        int[] num = new int[45];
        //초기화
        for (int i=0; i<num.length; i++) {
            num[i] = i+1;
        }
        int[] LottoNum = new int[6];
        for (int i=0; i<LottoNum.length; i++) {
            int seq = random.nextInt(45-i); //45범위 안에서 점점 좁힌다

            LottoNum[i] = num[seq]; //배열 범위에 맞는 값을 로또 번호로 대입한다.

            //뽑힌 값은 맨뒤로 보내고 i가 커짐에 따라 num 배열범위가 좁혀진다.
            int temp = num[seq];
            num[seq] = num[45-1-i];
            num[45-1-i] = temp;
        }
        return Arrays.toString(LottoNum);
    }
}

public class LottoMain {
    public static void main(String[] args) {
        Lotto lotto = new Lotto();
        lotto.start();
    }
}
