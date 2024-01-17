package org.example.config;

import com.google.common.collect.ImmutableList;
import lombok.Getter;

import java.util.List;

@Getter
public final class Config {
  private static Config instance = null;

  private Config() {
  }

  public static synchronized Config config() {
    if (instance == null)
      instance = new Config();

    return instance;
  }

  // initial.counts
  private final int playersCount = 5;
  private final int initialCalories = 5;

  // food.variants
  private final List<Integer> caloriesList = ImmutableList.of(0, 1, 2);
  private final double poisonProbability = 0.2;

  // flow.details
  private final int roundPayment = 1;
  private final boolean multipleStealingAllowed = true;
}
