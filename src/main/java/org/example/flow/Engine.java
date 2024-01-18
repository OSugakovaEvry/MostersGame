package org.example.flow;

import org.example.config.RuleConfig;
import org.example.data.Packet;
import org.example.data.Player;
import org.example.provider.FortuneProvider;

import java.util.List;
import java.util.Map;

import static org.example.config.Config.config;

public class Engine {
  private final RuleConfig ruleConfig;

  public Engine(RuleConfig ruleConfig) {
    this.ruleConfig = ruleConfig;
  }

  public void run(List<Player> players, Map<Integer, Packet> food) {
    players = reorder(players);
    int thiefIdx = FortuneProvider.getThiefIdx(players.size());
    for (int i = 0; i < players.size(); i++) {

      Player player = players.get(i);
      Packet packet = food.get(player.getId());

      if (i == thiefIdx) {
        // steal and eat
        Packet stolenDish = steal(players, food, i);
        player.eat(packet, stolenDish);

      } else {
        if (packet == null) {
          if (ruleConfig.isSpecial() && isNotLast(players, i)) {

            // steal
            Packet stolenPacket = steal(players, food, i);
            player.eat(stolenPacket);

          }
        } else {
          // eat
          player.eat(packet);
        }
      }

      player.pay(config().getRoundPayment());
    }
  }

  private static boolean isNotLast(List<Player> players, int i) {
    return i < players.size() - 1;
  }

  private List<Player> reorder(List<Player> players) {
    List<Player> reordered = players.stream().sorted().toList();
    System.out.println("Ordered Players: " + reordered);
    return reordered;
  }

  private static Packet steal(List<Player> players, Map<Integer, Packet> foodMap, int i) {
    int robbedIdx = FortuneProvider.getRobbedIdx(i, players.size());
    int playerId = players.get(robbedIdx).getId();

    Packet stolenPacket = foodMap.get(playerId);
    foodMap.put(playerId, null);

    System.out.printf("Player [%s] steal [%s] from [%s]%n",
            players.get(i).toString(),
            stolenPacket.toString(),
            players.get(robbedIdx).toString());

    return stolenPacket;
  }
}
