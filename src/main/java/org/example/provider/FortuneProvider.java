package org.example.provider;

import java.util.Random;

public class FortuneProvider {
  public static final Random random = new Random();

  public static int getThiefIdx(int size) {
    if (size < 2) {
      throw new IllegalArgumentException("Wrong arguments");
    }
    return random.nextInt(size - 1); // can't be last
  }

  public static int getRobbedIdx(int idx, int size) {
    int fromIdx = idx + 1; // can't be itself
    if (idx == size  || size < 2) {
      throw new IllegalArgumentException("Wrong arguments");
    }
    return random.nextInt(fromIdx, size);
  }

  public static double getSuccessProbability() {
    return random.nextDouble(0.4, 1); // more.likely
  }
}
