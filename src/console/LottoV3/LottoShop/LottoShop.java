package console.LottoV3.LottoShop;

import console.LottoV3.DrawLotto.Ball;

import java.util.*;

public class LottoShop {

    List<String> savedLotto = new ArrayList<String>(); //구매한 로또를 저장할 임시저장소
    private ArrayList<Ball> lotto;

    private Scanner sc = new Scanner(System.in);
    public void start() {
        System.out.println("-------------------------------------");
        System.out.println("구입할 로또 개수를 입력하시오.");
        System.out.println("-------------------------------------");
        int getCnt = Integer.parseInt(sc.nextLine());

        for (int i=1; i<=getCnt; i++) {
            System.out.println(i+"번째 로또 번호" + lottoNum().get(i-1));
        }
    }

    //로직: 랜덤으로 판매할 로또를 만든다.
    public List<String> lottoNum() {
        Random random = new Random();
        int[] num = new int[45];
        //초기화
        for (int i=0; i<num.length; i++) {
            num[i] = i+1;
        }
        int[] LottoNum = new int[7];
        for (int i=0; i<LottoNum.length; i++) {
            int seq = random.nextInt(45-i); //45 값 범위 안으로 좁힌다.
            LottoNum[i] = num[seq]; //배열 범위에 맞는 값을 로또 번호로 대입한다.
            //뽑힌 값은 맨뒤로 보내고 i가 커짐에 따라 num 배열범위가 좁혀진다.
            int temp = num[seq];
            num[seq] = num[45-1-i];
            num[45-1-i] = temp;
        }
        String IsLotto = Arrays.toString(LottoNum);
        savedLotto.add(IsLotto);
        return savedLotto;
    }
}
