package org.example.flow;

import lombok.AllArgsConstructor;
import org.example.data.Packet;
import org.example.data.Player;
import org.example.provider.FortuneProvider;

import java.util.List;
import java.util.Map;

import static org.example.config.Config.config;

public class Round {
  private List<Player> players;
  private Map<Integer, Packet> food;

  public Round(List<Player> players, Map<Integer, Packet> food) {
    this.players = players.stream().sorted().toList();
    this.food = food;
  }

  public void run() {
    int thiefIdx = FortuneProvider.getThiefIdx(players.size());
    for (int i = 0; i < players.size(); i++) {

      Player player = players.get(i);
      Packet packet = food.get(player.getId());

      if (i == thiefIdx) {
        Packet stolenDish = steal(players, food, i);
        if (stolenDish.isPoison()) {
          packet.setPoison(true);
        }
        player = player.eat(packet);
        player = player.eat(stolenDish);
      } else {
        if (packet == null) {
          if (config().isMultipleStealingAllowed() && i < players.size() - 1) {
            Packet stolenPacket = steal(players, food, i);
            player = player.eat(stolenPacket);
          }
        } else {
          player = player.eat(packet);
        }
      }

      player.pay(config().getRoundPayment());
    }
  }

  private void reorder(List<Player> players) {
    this.players = players.stream().sorted().toList();
    System.out.println("Ordered Players: " + players);
  }

  private static Packet steal(List<Player> players, Map<Integer, Packet> foodMap, int i) {
    int robbedIdx = FortuneProvider.getRobbedIdx(i, players.size());
    Integer playerId = players.get(robbedIdx).getId();
    Packet stolenPacket = foodMap.get(playerId);
    foodMap.put(playerId, null);
    System.out.printf("Player [%s] steal [%s] from [%s]%n", players.get(i).toString(), stolenPacket.toString(), players.get(robbedIdx).toString());
    return stolenPacket;
  }
}
