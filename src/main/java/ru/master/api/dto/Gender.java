package ru.master.api.dto;

import org.jetbrains.annotations.NotNull;

public enum Gender {
  MALE,
  FEMALE;

  @NotNull
  public static Gender fromString(@NotNull String gender) {
    switch (gender) {
      case "M" :
      case "m" :
      case "М":
      case "м":
        return MALE;
      case "F" :
      case "f" :
      case "W" :
      case "w":
      case "Ж" :
      case "ж":
        return FEMALE;
      default: throw new IllegalArgumentException("Неизвестный пол: " + gender);
    }
  }
}
