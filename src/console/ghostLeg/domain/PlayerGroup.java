package console.ghostLeg.domain;

import java.util.ArrayList;
import java.util.List;

public class PlayerGroup {

    private List<Player> players;
    private PlayerGenerator playerGenerator;

    public PlayerGroup(String playerNames) {
        this.playerGenerator = new PlayerGenerator();
        this.players = playerGenerator.convertToPlayerGroup(playerNames);

    }

    public List<String> getPlayerNameList() {
        List<String> names = new ArrayList<>();
        for (Player player : players) {
            names.add(player.getName());
        }
        return names;
    }

    public String searchPlayer(int index) {
        return players.get(index).getName();
    }
}
