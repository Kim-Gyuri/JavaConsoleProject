package console;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class myLotto {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Random random = new Random(); //랜덤 발생
        int[] lotto = new int[7]; //로또 번호를 저장한다.
        int getNum; //랜덤객체로 발생한 값 저장 변수

        //로또 번호 랜덤 추첨
        int i=0;
        while(i<lotto.length) {
            getNum = (int) (Math.random()*45)+1;
            System.out.println(i +":" + getNum);
            for (int j=0; j<i; j++) {
              if (getNum == lotto[j]) {
                  continue;
              }
            }
            lotto[i] = getNum;
            i++;
        }

        for (int j=0; j<lotto.length; j++) {
            if (j == lotto.length-1) {
                System.out.println("보너스 번호 추첨 :" + lotto[j]);
            } else {
                System.out.print(lotto[j]+" ");
            }
        }

        //이번 주 로또 번호 입력
        int[] arr = new int[7];
        for (int k=1; k<=lotto.length; k++) {
            System.out.printf("이번 주 로또 번호를 입력하세요 %d 's num: ", k);
            arr[k-1] = sc.nextInt();
            System.out.println();

        }
        System.out.println(Arrays.toString(lotto));
        System.out.println(Arrays.toString(arr));
        //일치하는 로또 번호를 계산하자!
        int count = 0;
        count = getCount(lotto, arr, count);
        System.out.println(count+"개 당첨되었습니다.");
    }

    private static int getCount(int[] lotto, int[] arr, int count) {
        for (int x=0; x<7; x++) {
            for (int y=0; y<7; y++) {
                if (lotto[x] == arr[y]) {
                    count++;
                }
            }
        }
        return count;
    }

}
