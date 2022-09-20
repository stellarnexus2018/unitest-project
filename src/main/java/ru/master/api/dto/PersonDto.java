package ru.master.api.dto;

import lombok.Builder;
import lombok.Data;

/**
 * DTO для представления "Пользователь"
 */
@Data
@Builder
public class PersonDto {
  private String  firstName;
  private Integer age;
}