package org.example.service;

import java.util.Random;

public class Fortune {
  private static final Random random = new Random();

  public static int getThiefIdx(int size) {
    return random.nextInt(size - 1);
  }

  public static int getRobbedIdx(int idx, int size) {
    int fromIdx = idx + 1; // can't be itself
    return random.nextInt(fromIdx, size);
  }

  public static double getPlayerSuccessProbability() {
    return random.nextDouble(0.4, 1); // more.likely
  }
}
