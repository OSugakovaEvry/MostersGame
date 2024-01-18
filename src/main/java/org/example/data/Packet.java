package org.example.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Packet {
  private int calories;

  public boolean isPoison() {
    return false;
  }
}
