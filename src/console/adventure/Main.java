package console.adventure;

import console.adventure.controller.GameController;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        GameController gameController = new GameController();
        gameController.startGame();
    }

}
