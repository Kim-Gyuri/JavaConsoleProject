package console.ghostLeg.controller;

import console.ghostLeg.domain.GhostLegField;
import console.ghostLeg.domain.PlayerGroup;
import console.ghostLeg.view.InputView;
import console.ghostLeg.view.ResultView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GhostLegField field = new GhostLegField(InputView.numOfPlayers(scanner), InputView.thingsOfBottom(scanner));
        PlayerGroup players = new PlayerGroup(InputView.playerName(scanner));
        ResultView.result(field, players);

    }
}
