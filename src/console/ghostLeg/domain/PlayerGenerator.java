package console.ghostLeg.domain;


import console.ghostLeg.util.SplitGenerator;

import java.util.ArrayList;
import java.util.List;

public class PlayerGenerator {

    private int index = 0;


    public List<Player> convertToPlayerGroup(String playerNames) {
        List<Player> playerArr = new ArrayList<>();

        for (String playerName : SplitGenerator.splitWithSign(playerNames, " ")) {
            playerArr.add(new Player(playerName, index+1));
        }
        return playerArr;
    }


}
