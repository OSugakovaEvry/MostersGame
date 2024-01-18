package org.example.flow;

import org.example.config.RuleConfig;
import org.example.data.DataGenerator;
import org.example.data.Packet;
import org.example.data.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EngineTest {
  private List<Player> players;
  private Map<Integer, Packet> food;

  @Before
  public void setUp() {
    players = DataGenerator.initPlayers();
    food = DataGenerator.initFoodMap();
  }

  @ParameterizedTest
  @EnumSource(RuleConfig.class)
  public void testTestRun(RuleConfig config) {
    Engine engine = new Engine(config);
    assertThatThrownBy(
            () ->
                    engine.run(emptyList(), emptyMap())
    )
            .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void testRunEngineWithoutErrors() {
    Engine engine = new Engine(RuleConfig.GENERAL);
    assertThatNoException().isThrownBy(() -> engine.run(players, food));
  }
}
