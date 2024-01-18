package org.example.flow;

import junit.framework.TestCase;
import org.example.config.RuleConfig;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EngineTest extends TestCase {

  public void testTestRun() {
    Engine engine = new Engine(RuleConfig.SPECIAL);
    assertThatThrownBy(
            () -> {
              engine.run(Collections.emptyList(), Collections.emptyMap());
            })
            .isInstanceOf(Exception.class);
  }
}
