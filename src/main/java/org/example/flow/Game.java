package org.example.flow;

import org.example.config.RuleConfig;
import org.example.data.Packet;
import org.example.data.Player;
import org.example.provider.DataProvider;

import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;

public class Game {
  private final Engine engine;

  public Game(RuleConfig modeEnum) {
    this.engine = new Engine(modeEnum);
  }

  public Player play() {
    List<Player> players = initPlayers();

    while (players.size() > 1) {
      System.out.println("=== Round === ");
      engine.run(players, initFood(players));

      players = grabSurvives(players);
    }
    return players.getFirst();
  }

  private List<Player> initPlayers() {
    List<Player> players = DataProvider.getPlayers();
    System.out.println("Players: " + players);
    return players;
  }

  private Map<Integer, Packet> initFood(List<Player> players) {
    List<Integer> playersIds = grabIds(players);

    Map<Integer, Packet> food = DataProvider.getFood(playersIds);
    System.out.println("Food: " + food);

    return food;
  }

  private List<Integer> grabIds(List<Player> players) {
    return players.stream()
            .map(Player::getId)
            .toList();
  }

  private List<Player> grabSurvives(List<Player> players) {
    List<Player> survives = players.stream()
            .filter(Player::isAlive)
            .toList();

    if (survives.isEmpty()) {
      survives = singletonList(players.getLast());
    }

    System.out.println("Survives: " + players);
    return survives;
  }

}
