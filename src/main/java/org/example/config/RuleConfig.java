package org.example.config;

public enum RuleConfig {
  GENERAL,
  SPECIAL;

  public boolean isSpecial() {
   return this == SPECIAL;
  }
}
