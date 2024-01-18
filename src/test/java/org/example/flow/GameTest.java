package org.example.flow;

import org.example.config.RuleConfig;
import org.example.data.Player;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GameTest {

  @ParameterizedTest
  @EnumSource(RuleConfig.class)
  public void testPlay(RuleConfig ruleConfig) {
    Game game = new Game(ruleConfig);
    Player winner = game.play();

    assertNotNull(winner);
  }
}
