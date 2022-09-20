package ru.master.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import ru.master.api.dto.PersonDto;
import ru.master.api.service.EngineOneService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class UnitestProjectApplicationTests {
  @Autowired
  private EngineOneService engineOne;

  @Test
  void testDataFind() {

    List<String> lstNames = List.of("Игорь10", "Игоряша", "Игорь");
    List<PersonDto> lstPersons = List.of(
        PersonDto
            .builder()
            .firstName("Игорюля")
            .age(20)
            .build(),
        PersonDto
            .builder()
            .firstName("Игорь")
            .age(10)
            .build(),
        PersonDto
            .builder()
            .firstName("Игорямба")
            .age(30)
            .build()
        );

    String result = engineOne.enrollProcess(lstNames, lstPersons);

    Assertions.assertEquals("Игорь", result);
  }

  @Test
  void testSample() {

    int[] arr1 = {1, 2, 3, 4, 3, 6, 8, 5};
    int[] arr2 = {3, 5, 7, 1, 3, 8, 2, 2};

    int[] arr3 = Stream.of(arr1, arr2)
        // выкидываем из каждого массива повторяющиеся
        // элементы и объединяем в один Stream<Integer>
        .flatMap(arr -> Arrays.stream(arr).distinct().boxed())
        // группируем в карту по количеству вхождений
        .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
        // обходим карту
        .entrySet().stream()
        // количество больше одного
        .filter(e -> e.getValue() > 1)
        // получаем сами элементы
        .map(e -> e.getKey())
        // получаем 'int' значения элементов
        .mapToInt(Integer::intValue)
        // собираем в массив
        .toArray();

// выводим массив повторяющихся элементов
    System.out.println(Arrays.toString(arr3)); // [1, 2, 3, 5, 8]


  }
}