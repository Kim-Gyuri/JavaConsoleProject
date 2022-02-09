package console.LottoV4.domain;

public class Ball {
    public static final int MINIMUM = 1;
    public static final int MAXIMUM = 45;
    private final int number;

    public Ball(int value) {
        this.number = value;
    }

    public int getNumber() {
        return this.number;
    }
}
