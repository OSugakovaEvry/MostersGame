package org.example.service;

import org.example.model.Packet;
import org.example.model.Player;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.function.Function.identity;
import static org.example.config.Config.config;

public class Provider implements Print {

  public List<Player> getPlayers() {
    List<Player> players = IntStream.range(0, config().getPlayersCount())
            .mapToObj(Player::new)
            .collect(Collectors.toList());
//    print(players);
    return players;
  }

  public Map<Integer, Packet> getFood(List<Integer> playersIds) {
    double poisonProbability = config().getPoisonProbability();
    List<Integer> calories = config().getCaloriesList();

    if (poisonProbability < 0 || poisonProbability > 1 || calories.isEmpty()) {
      throw new IllegalArgumentException("Wrong configuration parameters");
    }

    Map<Integer, Packet> food = playersIds.stream()
            .collect(Collectors.toMap(identity(), p -> getPortion(poisonProbability, calories)));
//    print(food);
    return food;
  }

  public Packet getPortion(double poisonProbability, List<Integer> calories) {
    Random random = new Random();
    int nextInt = random.nextInt(calories.size());

    return Packet.builder()
            .calories(calories.get(nextInt))
            .isPoison(random.nextDouble() <= poisonProbability)
            .build();
  }

  @Override
  public void print(Map<?, ?> map) {
    System.out.println("Food: " + map);
  }

  @Override
  public void print(Collection<?> collection) {
    System.out.println("Players: " + collection);
  }
}
