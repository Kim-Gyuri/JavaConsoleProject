package console.LottoV4.domain.ticketgenerator;


import console.LottoV4.domain.Board;
import console.LottoV4.domain.BoardDao;
import console.LottoV4.domain.LottoNumber;
import console.LottoV4.result.WinningLottoNumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Generator {

    public static final int MINIMUM = 1;
    public static final int MAXIMUM = 45;
    private final List<LottoNumber> allNumbers;
    private final List<LottoNumber> winningLottoNumbers;

    public Generator() {
        this.winningLottoNumbers = new ArrayList<>();
        this.allNumbers = new ArrayList<>();
        for (int i = LottoNumber.MINIMUM; i <= LottoNumber.MAXIMUM; i++) {
            allNumbers.add(new LottoNumber(i));
        }
    }


    //6개를 무작위로 뽑기
    public BoardDao generateTickets(int numberOfTickets) {
        BoardDao dao = new BoardDao();
        for (int x = 0; x < numberOfTickets; x++) {
            Board newBoard = createSixBolls();
            dao.add(newBoard);
        }
        return dao;
    }

    //추첨: 7개 자동 로또추첨 뽑기
    public WinningLottoNumbers generateWinningLotto() {
        WinningLottoNumbers winningBolls = createWinningBolls();
        return winningBolls;
    }

    private Board createSixBolls() {
        Collections.shuffle(allNumbers);
        Board newBoard = new Board(allNumbers.subList(0, 6));
        return newBoard;
    }

    private WinningLottoNumbers createWinningBolls() {
        WinningLottoNumbers winningLottoNumbers = new WinningLottoNumbers();
        Collections.shuffle(allNumbers);
        Board newBoard = new Board(allNumbers.subList(0, 6));
        LottoNumber lottoNumber = allNumbers.get(7);
        winningLottoNumbers.getWinningNumber(newBoard, lottoNumber);
        return winningLottoNumbers;
    }



}
