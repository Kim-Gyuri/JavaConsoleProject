package console.LottoV4.domain.ticketgenerator;

import console.LottoV4.domain.Buyer;

public class LottoMachine {

    private final LottoGenerator lottoGenerator;

    public LottoMachine() {
        this.lottoGenerator = new LottoGenerator();
    }

    public Buyer purchaseLottoTickets(int numberOfTickets) {
        return lottoGenerator.generateTickets(numberOfTickets);
    }

}
