package console.LottoV4.result;

import console.LottoV4.domain.Buyer;
import console.LottoV4.winnigsType.WinningsType;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private static final int ZERO = 0;
    private final WinningLottoNumber winningLottoNumber;
    private final Map<WinningsType, Integer> resultCounts;

    public LottoResult(WinningLottoNumber winningLottoNumber, Buyer buyer) {
        this.winningLottoNumber = winningLottoNumber;
        this.resultCounts = new HashMap<>();
        initLottoCount();

    }

    private void initLottoCount() {
        for (WinningsType winningsType : WinningsType.values()) {
            resultCounts.put(winningsType, ZERO);
        }
    }

    public int getCountOfMatchedNumber(WinningsType matchType) {
        return resultCounts.get(matchType);
    }

}
