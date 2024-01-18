package org.example;

import org.example.config.RuleConfig;
import org.example.data.Player;
import org.example.flow.Game;

import static java.util.Optional.ofNullable;
import static org.example.config.Config.config;
import static org.example.config.RuleConfig.GENERAL;
import static org.example.config.RuleConfig.SPECIAL;

public class Main {

  public static void main(String[] args) {
    System.out.println("Welcome to the Monsters Game!");
    System.out.println("=== Start ===\n\n");

    Player winner = new Game(getRuleConfig(args))
            .play();

    System.out.println("=== End ===\n\n");
    System.out.printf("Game is over! Player [%s] is won, calories count: [%d]. Congrats!!%n",
            winner.getId(),
            winner.getCalories());
  }

  private static RuleConfig getRuleConfig(String[] args) {
    boolean isAllowed = ofNullable(args[0]).stream()
            .anyMatch(config().getMultipleStealingFlag()::equalsIgnoreCase);

    RuleConfig ruleConfig = isAllowed
            ? SPECIAL
            : GENERAL;

    System.out.printf("Multiple stealing allowed: %s, mode: %s", isAllowed, ruleConfig);
    return ruleConfig;
  }
}
