package console.LottoV4.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LottoTicket {
    private static final int SIZE = 6;

    private final List<Ball> balls;

    public LottoTicket(List<Ball> balls) {
        this.balls = new ArrayList<>();
        this.balls.sort(Comparator.comparingInt(Ball::getNumber));
    }

    public List<Ball> getLottoTicketNum() {
        return Collections.unmodifiableList(this.balls);
    }

    public boolean hasBonusBall(Ball ball) {
        return balls.contains(ball);
    }
}
