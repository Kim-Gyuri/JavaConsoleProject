package console.LottoV4.domain;

import java.util.Objects;

//로또 번호 범위
public class LottoNumber {
    public static final int MINIMUM = 1;
    public static final int MAXIMUM = 45;
    private final int number;

    public LottoNumber(int value) {
        this.number = value;
    }

    public int getNumber() {
        return this.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LottoNumber)) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}