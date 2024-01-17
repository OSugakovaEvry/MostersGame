package org.example.data;

import lombok.Getter;
import lombok.ToString;
import org.example.provider.FortuneProvider;

import static org.example.config.Config.config;

@Getter
@ToString
public class Player implements Comparable<Player> {
  private final int id;
  private int calories;

  public Player(int id) {
    this.id = id;
    this.calories = config().getInitialCalories();
  }

  public void pay(int payment) {
    this.calories -= payment;
  }

  public Player eat(Packet packet) {
    if (packet.isPoison()) {
      System.out.printf("Player %d has been poisoned, [-%d] calories%n", this.getId(), packet.getCalories());
      calories -= packet.getCalories();
    } else {
      calories += packet.getCalories();
    }
    return this;
  }

  public Player eat(Packet dish, Packet stolenDish) {
    if (stolenDish.isPoison()) {
      dish.setPoison(true);
    }
    this.eat(dish);
//    Packet newPacket = Packet.builder()
//            .calories(dish.getCalories() + stolenDish.getCalories())
//            .isPoison(stolenDish.isPoison())
//            .build();

    this.eat(stolenDish);
    return this;
  }

  public boolean isAlive() {
    return this.getCalories() > 0;
  }

  @Override
  public int compareTo(Player otherPlayer) {
    double probability = FortuneProvider.getPlayerSuccessProbability();
    return Double.compare(otherPlayer.getCalories() * probability, this.getCalories() * probability);
  }
}
