package ru.master.api.service.impl;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.master.api.dto.PersonDto;
import ru.master.api.service.EngineOneService;
import java.util.List;
import java.util.stream.Collectors;

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

  @NonNull
  public List<String> enrollCrossing(@NonNull List<String> names, @NonNull List<PersonDto> persons) {
    log.info("Список имён: {}", names);
    log.info("Список пользователей: {}", persons);


    final List<String> collect = names
        .stream()
        .filter(n -> !persons
            .stream()
            .map(PersonDto::getFirstName).collect(Collectors.toList()).contains(n))
        .collect(Collectors.toList());


    /*final var coll = persons
        .stream()
        .map(PersonDto::getFirstName)
        .distinct()
        .peek(n->System.out.println("после map1: " + n))
        .flatMap(x -> names.stream()
            .filter(y -> !x.equals(y)).limit(1).peek(m -> System.out.println("in flatMap: " + m)))
        .peek(m -> System.out.println("после flatMap: " + m))
        .collect(Collectors.toList());*/

    log.info("Результат: {}", collect);

    return collect;
  }
}