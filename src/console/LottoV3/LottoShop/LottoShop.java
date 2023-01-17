package console.LottoV3.LottoShop;


import console.LottoV3.Generator.LottoCalculator;

import java.util.ArrayList;
import java.util.Scanner;

//로또 판매하는 곳 객체
public class LottoShop {

    private final static int MAX_RANGE = 6; //로또 구매시, 최대 6개의 숫자를 적는다.

    private ArrayList<Lotto> lottos; //손님 1인이 구매한 총 로또를 임시 보관

    public LottoShop() {
        lottos = new ArrayList<Lotto>();
    }

    private Scanner sc = new Scanner(System.in);


    // 로또를 판다.
    public ArrayList<Lotto> sell() {
        System.out.println("-------------------------------------");
        System.out.println("구입할 로또 개수를 입력하시오.");
        System.out.println("-------------------------------------");

        int getCnt = sc.nextInt();

        for (int i=0; i<getCnt; i++) {
            lottos.add(new Lotto(getLottoNumber()));
        }
        return lottos;
    }

    // 1장당 6개의 숫자를 랜덤으로 뽑는 메소드
    public ArrayList<Integer> getLottoNumber() {
        ArrayList<Integer> lotto = new ArrayList<>();
        LottoCalculator lc = new LottoCalculator();
        lc.setBalls();

        for (int i=0; i<MAX_RANGE; i++) {
            lotto.add(lc.getBall());
        }
        return lotto;
    }

    // 구매한 로또를 출력해준다.
    public void printLotto() {
        System.out.println("내가 산 로또!");
        for (Lotto mine : lottos) {
            System.out.println("[" + mine.toString() + "]");
        }
    }


}
