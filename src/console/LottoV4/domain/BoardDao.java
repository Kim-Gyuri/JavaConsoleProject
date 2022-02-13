package console.LottoV4.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//내가 총 구매한 티켓 (총 티켓 개수)
public class BoardDao {

    private List<Board> list;

    public BoardDao() {
        this.list = new ArrayList<>();
    }
    public BoardDao(List<Board> list) {
        this.list = list;
    }

    public List<Board> getList() {
        return Collections.unmodifiableList(list);
    }

    public void add(Board board) {
        list.add(board);
    }

}
