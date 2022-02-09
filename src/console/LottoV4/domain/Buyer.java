package console.LottoV4.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
(구매자) 구매한 로또를 소지한다.
numberOfTickets :구매할 로또 개수
 */
public class Buyer {

    private int numberOfTickets;
    private List<LottoTicket> tickets;

    public Buyer(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public Buyer(List<LottoTicket> tickets) {
        this.tickets = tickets;
    }

    public Buyer() {
        this.tickets = new ArrayList<>();
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }


    public List<LottoTicket> getTicket() {
        return Collections.unmodifiableList(tickets);
    }

    public void buyTicket(LottoTicket lottoTicket) {
        tickets.add(lottoTicket);
    }
}
