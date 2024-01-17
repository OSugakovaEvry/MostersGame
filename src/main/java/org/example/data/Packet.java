package org.example.data;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@ToString
@Builder
public class Packet {
  private int calories;
  private boolean isPoison;
}
