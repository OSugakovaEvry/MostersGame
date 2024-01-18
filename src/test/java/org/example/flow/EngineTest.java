package org.example.flow;

import org.example.config.RuleConfig;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EngineTest {

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
}
