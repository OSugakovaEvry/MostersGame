package org.example;

import org.example.data.Player;
import org.example.flow.Game;
import org.example.config.RuleConfig;

import java.util.Optional;

public class Main {

  public static void main(String[] args) {
    System.out.println("Welcome to the Monsters Game!");
    System.out.println("=== Start ===\n\n");

    RuleConfig ruleConfig = getRuleConfig(args);
    Player winner = new Game(ruleConfig)
            .play();

    System.out.println("=== End ===\n\n");
    System.out.printf("Game is over! Player [%s] is won, calories count: [%d]. Congrats!!%n",
            winner.getId(),
            winner.getCalories());
  }

  private static RuleConfig getRuleConfig(String[] args) {
    boolean specialMode = Optional.ofNullable(args[0]).stream()
            .anyMatch("multipleStealingAllowed"::equalsIgnoreCase);
    return specialMode ? RuleConfig.SPECIAL : RuleConfig.GENERAL;
  }

  private static boolean isMultiplyStealingAllowed(String[] args) {
    return Optional.ofNullable(args[0]).stream()
            .anyMatch("multipleStealingAllowed"::equalsIgnoreCase);
  }
}
