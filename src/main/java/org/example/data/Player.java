package org.example.model;

import lombok.Getter;
import lombok.ToString;
import org.example.config.Config;
import org.example.service.Fortune;

import java.util.Random;

@Getter
@ToString
public class Player implements Comparable<Player> {
  private final int id;
  private int calories;

  public Player(int id) {
    this.id = id;
    this.calories = Config.config().getInitialCalories();
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
    return eat(new Packet(dish.getCalories() + stolenDish.getCalories(), stolenDish.isPoison()));
  }

  public boolean isAlive() {
    return this.getCalories() > 0;
  }

  @Override
  public int compareTo(Player otherPlayer) {
    double probability = Fortune.getPlayerSuccessProbability();
    return Double.compare(otherPlayer.getCalories() * probability, this.getCalories() * probability);
  }
}
