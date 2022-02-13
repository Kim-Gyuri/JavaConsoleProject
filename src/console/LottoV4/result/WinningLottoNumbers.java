package console.LottoV4.result;

import console.LottoV4.domain.Board;
import console.LottoV4.domain.LottoNumber;

public class WinningLottoNumbers {

    private Board board;
    private LottoNumber bonusNumber;


    public WinningLottoNumbers(Board board, LottoNumber bonusNumber) {
        validateWinningTicketNotContainBonusNumber(board, bonusNumber);
        this.board = board;
        this.bonusNumber = bonusNumber;
    }

    public WinningLottoNumbers() {

    }

    public Board getBoard() {
        return board;
    }


    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    private void validateWinningTicketNotContainBonusNumber(Board winningTicket, LottoNumber bonusNumber) {
        if (winningTicket.getLottoTicketNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호에 포함되지 않습니다.");
        }
    }

    public void getWinningNumber(Board winningTicket, LottoNumber bonusBoll) {
        this.board = winningTicket;
        this.bonusNumber = bonusBoll;
    }

}
