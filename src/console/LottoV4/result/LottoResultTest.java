package console.LottoV4.result;


import console.LottoV4.domain.Board;
import console.LottoV4.domain.BoardDao;
import console.LottoV4.domain.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class LottoResultTest {

    private WinningLottoNumbers winningLottoNumbers;
    private long count;
    private List<Long> matchSize = new ArrayList<>();

    @BeforeEach
    void setWinningLottoNumbers() {
        Board winnerTicket = new Board(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)));

        LottoNumber bonusNumber=  new LottoNumber(7);
        winningLottoNumbers = new WinningLottoNumbers(winnerTicket, bonusNumber);
    }

    @Test
    void getCount() {
        //List 생성
       Board board1 = new Board(Arrays.asList(
               new LottoNumber(1),
               new LottoNumber(2),
               new LottoNumber(3),
               new LottoNumber(4),
               new LottoNumber(5),
               new LottoNumber(6)));

       Board board2 = new Board(Arrays.asList(
               new LottoNumber(1),
               new LottoNumber(2),
               new LottoNumber(3),
               new LottoNumber(4),
               new LottoNumber(5),
               new LottoNumber(11)));

        BoardDao dao = new BoardDao();
        dao.add(board1);
        dao.add(board2);

        Board windBoard = winningLottoNumbers.getBoard();


        List<Board> list = dao.getList();
        Iterator<Board> iterator = list.iterator();
        while(iterator.hasNext()) {
            Board next = iterator.next();
            count = next.getLottoTicketNumbers()
                    .stream()
                    .filter(purchased -> windBoard.getLottoTicketNumbers()
                            .contains(purchased))
                    .count();
            System.out.println("count = " + count);
            matchSize.add(count);
        }
        System.out.println("총 count = " + matchSize.toString());

    }

}