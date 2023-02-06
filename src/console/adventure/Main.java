package console.adventure;


import console.adventure.domain.GameSystem;
import console.adventure.view.View;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        GameSystem system = new GameSystem();
        //system -> player, play.
        View.playerCharacter(scanner, system);
        View.play(scanner, system);



    }
}
