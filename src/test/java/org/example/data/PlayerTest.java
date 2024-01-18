package org.example.data;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

  @Test
  public void testEatOwnPacketPoisoned() {
    Player player = new Player(5);

    Packet ownPacket = new PoisonedPacket(1);
    Packet stolenPacket = new Packet(2);

    player = player.eat(ownPacket, stolenPacket);

    assertEquals(6, player.getCalories());
  }

  @Test
  public void testStealPoisoned() {
    Player player = new Player(5);

    Packet ownPacket = new Packet(1);
    Packet stolenPacket = new PoisonedPacket(2);

    player = player.eat(ownPacket, stolenPacket);

    assertEquals(player.getCalories(), 2);
  }
}
