package console.LottoV4.main.java.model;

// 로또 번호 객체
public class LottoNo {

    private int number;

    public LottoNo(int number) {
        if (number < 1  || number > 45)
            throw new IndexOutOfBoundsException("로또 번호 밖의 숫자입니다.");
        this.number = number;
    }

    public String getStringNumber() {
        return Integer.toString(number);
    }
}
