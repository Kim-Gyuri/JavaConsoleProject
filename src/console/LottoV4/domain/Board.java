package console.LottoV4.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//내가 작성한 로또 티켓 (1장 개념)
public class Board {
   private List<LottoNumber> tickets;

   public Board(List<LottoNumber> tickets) {
       this.tickets = new ArrayList<>(tickets);
   }
   public List<LottoNumber> getTickets() {
       return tickets;
   }

   //당첨번호 검사 -보너스번호 유무
    public List<LottoNumber> getLottoTicketNumbers() {
       return Collections.unmodifiableList(this.tickets);
    }


    public boolean hasBonusNumber(LottoNumber lottoNumber) {
        return tickets.contains(lottoNumber);
    }

    public void add(LottoNumber lottoNumber) {
        tickets.add(lottoNumber);
    }

}
