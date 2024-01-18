package org.example.data;

public class PoisonedPacket extends Packet {

  public PoisonedPacket(int calories) {
    super(calories * (-1));
  }

  @Override
  public boolean isPoison() {
    return getCalories() < 0;
  }
}
