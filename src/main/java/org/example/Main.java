package org.example;

import org.example.data.Player;
import org.example.flow.Game;

public class Main {

  public static void main(String[] args) {
    System.out.println("Welcome to the Monsters Game!");
    System.out.println("=== Start ===\n\n");

    Player winner = new Game()
            .play();

    System.out.println("=== End ===\n\n");
    System.out.printf("Game is over! Player [%s] is won, calories count: [%d]. Congrats!!%n",
            winner.getId(),
            winner.getCalories());
  }
}
