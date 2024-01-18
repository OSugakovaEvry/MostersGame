package org.example.provider;

import org.example.data.Packet;
import org.example.data.Player;
import org.example.data.PoisonedPacket;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static org.example.config.Config.config;
import static org.example.provider.FortuneProvider.random;

public class DataProvider {

  public static List<Player> getPlayers() {
    return IntStream.range(0, config().getPlayersCount())
            .mapToObj(Player::new)
            .toList();
  }

  public static Map<Integer, Packet> getFood(List<Integer> playersIds) {
    double poisonProbability = config().getPoisonProbability();
    List<Integer> calories = config().getCaloriesList();

    if (poisonProbability < 0 || poisonProbability > 1 || calories.isEmpty()) {
      throw new IllegalArgumentException("Wrong configuration parameters");
    }

    return playersIds.stream()
            .collect(toMap(identity(), p -> getPortion(poisonProbability, calories)));
  }

  private static Packet getPortion(double poisonProbability, List<Integer> calories) {
    int nextInt = random.nextInt(calories.size());

    return random.nextDouble() <= poisonProbability
            ? new PoisonedPacket(calories.get(nextInt))
            : new Packet(calories.get(nextInt));
  }
}
