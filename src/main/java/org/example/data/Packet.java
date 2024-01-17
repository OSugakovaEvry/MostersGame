package org.example.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

//@RequiredArgsConstructor
@Getter
@ToString
@Builder
public class Packet {
  private final int calories;
  private final boolean isPoison;
}
