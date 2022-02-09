package console.LottoV4.domain.ticketgenerator;

import console.LottoV4.domain.Ball;
import console.LottoV4.domain.Buyer;
import console.LottoV4.domain.LottoTicket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {

    private static final int ZERO = 0;
    private static final int LOTTO_NUMBERS_SIZE = 6;
    private final List<Ball> balls;

    private int[] ball = new int[LOTTO_NUMBERS_SIZE];
    public static List<Integer> savedBall = new ArrayList<>();//임시저장소
    private int cnt = 0;

    public LottoGenerator() {
        this.balls = new ArrayList<>();
        for(int i=Ball.MINIMUM; i<=Ball.MAXIMUM; i++) {
            balls.add(new Ball(i));
        }
    }
/*
    public Buyer generateTickets(int numberOfTickets) {
        Buyer buyer = new Buyer();
        for (int i=0; i<numberOfTickets; i++) {
            Collections.shuffle(balls);
            LottoTicket newLottoTicket = new LottoTicket(balls.subList(ZERO, LOTTO_NUMBERS_SIZE));
            buyer.buyTicket(newLottoTicket);
        }
        return buyer;
    }

 */
    public Buyer generateTickets(int numberOfTickets) {
        Buyer  buyer = new Buyer();
        for (int i=0; i<numberOfTickets; i++) {
            Collections.shuffle(balls);
            ball[i] = balls.get(cnt++).getNumber();

            LottoTicket newLottoTicket = new LottoTicket(balls.subList(ZERO, LOTTO_NUMBERS_SIZE));
            buyer.buyTicket(newLottoTicket);
        }
        return buyer;
    }
}
