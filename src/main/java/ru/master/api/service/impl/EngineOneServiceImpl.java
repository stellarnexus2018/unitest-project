package ru.master.api.service.impl;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.master.api.dto.PersonDto;
import ru.master.api.service.EngineOneService;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class EngineOneServiceImpl implements EngineOneService {
  /**
   * Выборка сравнения из двух списков
   *
   * @param names   Список имён
   * @param persons Список пользователей
   * @return Совпадение
   */
  @NonNull
  public String enrollProcess(@NonNull List<String> names, @NonNull List<PersonDto> persons) {
    log.info("Список имён: {}", names);
    log.info("Список пользователей: {}", persons);

    var name = names
        .stream()
        .flatMap(x -> persons
            .stream()
            .filter(y -> x.equals(y.getFirstName()))
            .limit(1))
        .findFirst()
        .orElse(PersonDto
            .builder()
            .firstName("Трабля")
            .age(0)
            .build());
        //.isPresent();

    return name.getFirstName();
  }


}
