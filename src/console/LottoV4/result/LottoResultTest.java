package console.LottoV4.result;


import console.LottoV4.domain.Board;
import console.LottoV4.domain.BoardDao;
import console.LottoV4.domain.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
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

       // Board windBoard = winningLottoNumbers.getBoard();
        LottoComparator comparator = new LottoComparator(winningLottoNumbers, dao);
        List<Integer> num1 = comparator.getNum(winningLottoNumbers, dao);

/*
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
*/
    }

    @Test
    void getMatchedType() {
        //List 생성
       Board board1 = new Board(Arrays.asList(
               new LottoNumber(1),
               new LottoNumber(2),
               new LottoNumber(3),
               new LottoNumber(4),
               new LottoNumber(5),
               new LottoNumber(10)));

       Board board2 = new Board(Arrays.asList(
               new LottoNumber(1),
               new LottoNumber(2),
               new LottoNumber(9),
               new LottoNumber(23),
               new LottoNumber(24),
               new LottoNumber(25)));

        Board board3 = new Board(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(7)));

/*
        Board board4 = new Board(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(20),
                new LottoNumber(30)));
*/

        BoardDao dao = new BoardDao();
        dao.add(board1);
        dao.add(board2);
        dao.add(board3);
  //      dao.add(board4);

        LottoComparator comparator = new LottoComparator(winningLottoNumbers, dao);
        comparator.getMatchedType(winningLottoNumbers, dao);



    }

}