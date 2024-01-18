package org.example.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataGenerator {

  public static Map<Integer, Packet> initFoodMap() {
    Map<Integer, Packet> food = new HashMap<>(5);
    food.put(0, new PoisonedPacket(-2));
    food.put(1, new Packet(1));
    food.put(2, new PoisonedPacket(0));
    food.put(3, new PoisonedPacket(1));
    food.put(4, new PoisonedPacket(2));
    return food;
  }

  public static List<Player> initPlayers() {
    List<Player> players = new ArrayList<>(5);
    players.add(new Player(0));
    players.add(new Player(1));
    players.add(new Player(2));
    players.add(new Player(3));
    players.add(new Player(4));
    return players;
  }
}
