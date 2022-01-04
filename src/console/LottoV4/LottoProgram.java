package console.LottoV4;

import java.util.*;

public class LottoProgram {

    public static final int LOTTO_NUMBER_MAX = 5;
    public static final int BONUS_NUMBER_INDEX = 6;

    public static final int THE_SIXTH = 6;
    public static final int THE_FIVE = 5;
    public static final int THE_FOUR = 4;
    public static final int THE_THREE = 3;

    private Iterator iterator;

    private ArrayList<Lotto> lotto;
    private ArrayList<Integer> winnings;
    private Integer bonusNumber;

    public LottoProgram() {
        lotto = new ArrayList<Lotto>();
    }

    public ArrayList<Lotto> getLotto() {
        return lotto;
    }

    public void setLotto(ArrayList<Lotto> lotto) {
        this.lotto = lotto;
    }

    public ArrayList<Integer> getWinnings() {
        return winnings;
    }

    public void setWinnings(ArrayList<Integer> winnings) {
        this.winnings = winnings;
    }

    public Integer getBonusNumber() {
        return bonusNumber;
    }

    public void setBonusNumber(Integer bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    //로또 번호 생성하기
    private ArrayList<Integer> getLottoNumber() {
        HashSet<Integer> temp = new HashSet<Integer>();

        while (temp.size() <= LOTTO_NUMBER_MAX) {
            //auto boxing
            temp.add((int) (Math.random()*45)+1);
        }

        ArrayList<Integer> list = new ArrayList<Integer>(temp);
        Collections.sort(list);
        return list;
    }

    private void getLottoResultNumber() {
        System.out.println("-------------------------------------");
        System.out.println("당첨번호를 추첨 합니다.");
        System.out.println("-------------------------------------");
        winnings = getLottoNumber();

        do {
            bonusNumber = (int) (Math.random() *45) + 1;
            if (!winnings.contains(bonusNumber)) {
                winnings.add(bonusNumber);
            }
        } while (winnings.size() == BONUS_NUMBER_INDEX);
        System.out.println("당첨 번호 : " + winnings);
        System.out.println("마지막 번호가 보너스 번호 입니다.");
    }

    //내가 로또를 사러간다.
    public void buyLotto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------------------------");
        System.out.printf("몇 개를 살까요: ");
        System.out.println();
        System.out.println("-------------------------------------");
        int num = sc.nextInt();

        for (int i=0; i<num; i++) {
            lotto.add(new Lotto(getLottoNumber()));
        }
    }

    //모든 로또 출력
    public void printAll() {
        int i = 1;
        iterator = lotto.iterator();

        while(iterator.hasNext()) {
            System.out.println(i+"회 번호: " + iterator.next());
            i++;
        }
    }

    //당첨 결과를 확인한다.
    public void result() {
        winnings.remove(BONUS_NUMBER_INDEX);
        iterator = lotto.iterator();
        int i = 1;
        while (iterator.hasNext()) {
            Lotto temp = (Lotto) iterator.next();
            ArrayList<Integer> lottoTemp = temp.getLotto();
            lottoTemp.retainAll(winnings);
            String result = rank(lottoTemp);
            System.out.println("-------------------------------------");
            System.out.println(i+"회 게임 결과는 " + result + "\t");
            System.out.println("일치 번호 ->" + lottoTemp);
            System.out.println("-------------------------------------");
            i++;
        }
    }

    //등수를 확인한다.
    private String rank(ArrayList<Integer> temp) {
        /* 1등 6개 일치
        2등 5개 + 보너스번호 일치
        3등 5개 일치
        4등 4개 일치
        5등 3개 일치
        */
        int count = temp.size();

        if (count == THE_SIXTH) {
            return "1등";
        } else if (count == THE_FIVE && temp.contains(bonusNumber)){
            return "2등";
        } else if (count == THE_FIVE) {
            return "3등";
        } else if (count == THE_FOUR) {
            return "4등";
        } else if (count == THE_THREE) {
            return "5등";
        } else {
            return "꽝";
        }
    }
    //Process
    public void process() { //1. 복권 몇 개를 구매할 건가(구매한 복권 다 출력함
        buyLotto();
        printAll();
        //2. 당첨번호 추첨
        getLottoResultNumber();
        //3. 게임결과 확인
         result();
    }

    public static void main(String[] args) {
        LottoProgram program = new LottoProgram();
        program.process();

    }

}
