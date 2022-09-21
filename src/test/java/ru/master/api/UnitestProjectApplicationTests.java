package ru.master.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import ru.master.api.dto.Gender;
import ru.master.api.dto.Person;
import ru.master.api.dto.PersonDto;
import ru.master.api.service.EngineOneService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
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

  @Test
  void test1() {
    /*String find = "Дожитие с возвратом взносов в случае смерти";*/
    String find = "Дожитие застрахованного лица (взрослый) до установленной даты";

    /*var lst = List.of(
        "Основная программа",
        "Дожитие с возвратом взносов в случае смерти",
        "Дожитие застрахованного лица (ребенок) до установленной даты",
        "Дожитие застрахованного лица (взрослый) до установленной даты"
    );*/

    var lst = new HashSet(List.of(
        "Основная программа",
        "Дожитие с возвратом взносов в случае смерти",
        "Дожитие застрахованного лица (ребенок) до установленной даты",
        "Дожитие застрахованного лица (взрослый) до установленной даты"
    ));



    lst.forEach(System.out::println);



    Optional<String> first = lst
        /*.parallelStream()*/
        .stream()
        .peek(System.out::println)
        .peek(lst::add/*i -> lst.add(i+"new")*/)
        .filter(s -> s.equals(find))
        .findFirst()
        .or(Optional::empty);

    /*first = Optional.empty();*/

    System.out.println("first: " + first.get());

    lst.forEach(System.out::println);


  }

  @Test
  void test2() {

    var lst1 = List.of(
        "Основная программа",
        "Дожитие с возвратом взносов в случае смерти"
    );

    var lst2 = List.of(
        "Основная программа",
        "Дожитие с возвратом взносов в случае смерти",
        "Дожитие застрахованного лица (ребенок) до установленной даты",
        "Дожитие застрахованного лица (взрослый) до установленной даты"
    );

    List<String> collect = lst1.stream().filter(n -> lst2.stream().anyMatch(m -> m.equals(n))).collect(Collectors.toList());
    System.out.println("collect: " + collect);
    /*collect.forEach(System.out::println);*/
  }

  @Test
  void test3() {
    var lstPersons = new ArrayList<Person>();
    lstPersons.add(new Person("Игоряша", 49));
    lstPersons.add(new Person("Игоряша", 10));
    lstPersons.add(new Person("Игорь", 49));

    var lstNames = List.of(
        "Игоряша",
        "Игорямба"
    );

    var result = lstNames
        .stream()
        .peek(e -> System.out.println("raw: " + e))
        .filter(n -> {
              boolean contains = lstPersons
                  .stream()
                  .map(Person::getName)
                  .peek(e -> System.out.println("internal: " + e))
                  .collect(Collectors.toList())
                  .contains(n);
              System.out.println("contains: " + contains);
              return contains;
            }
            /*.allMatch(m -> m.equals(n))*/
        )
        .peek(e -> System.out.println("filtered: " + e))
        .collect(Collectors.toList());
    System.out.println("result: " + result);
  }

  @Test
  void test4() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    LocalDate birth = LocalDate.of(1980, 7, 13);
    //LocalDate pol = LocalDate.of(2022, 9, 5);
    LocalDate pol = LocalDate.of(2022, 6, 5);

    LocalDate add70 = birth.plusYears(70);
    int birthDayOfYear = birth.getDayOfYear();
    int polDayOfYear = pol.getDayOfYear();

    System.out.println("birth: " + birth.format(formatter));
    System.out.println("pol: " + pol.format(formatter));
    System.out.println("add70: " + birth.plusYears(70).format(formatter));
    System.out.println("birthDayOfYear: " + birthDayOfYear);
    System.out.println("polDayOfYear: " + polDayOfYear);

    LocalDate result;

    if(polDayOfYear < birthDayOfYear) {
      result = LocalDate.of(birth.plusYears(70).getYear(), pol.getMonthValue(), pol.getDayOfMonth()).plusYears(1L).minusDays(1L);
    } else {
      result = LocalDate.of(birth.plusYears(70).getYear(), pol.getMonthValue(), pol.getDayOfMonth()).minusDays(1L);
    }

    System.out.println("result: " + result.format(formatter));

    /*birth: 13.07.1980
    pol: 05.09.2022
    add70: 13.07.2050
    birthDayOfYear: 195
    polDayOfYear: 248*/

    /*13.07.1980.
    05.06.2022 года
    Полис начал действие 05 сентября 2022 года.
    Тогда Последний день страхового года,
    когда застрахованному исполнится 70 лет – 04 сентября 2050 года.*/
  }

  @Test
  void test5() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    //String personSex = "M";
    String personSex = "F";

    LocalDate birth = LocalDate.of(1980, 7, 13);
    //LocalDate pol = LocalDate.of(2022, 9, 5);
    LocalDate pol = LocalDate.of(2022, 6, 5);

    LocalDate add65 = birth.plusYears(65);
    LocalDate add60 = birth.plusYears(60);
    //int birthDayOfYear = birth.getDayOfYear();
    //int polDayOfYear = pol.getDayOfYear();

    System.out.println("personSex: " + personSex);
    System.out.println("birth: " + birth.format(formatter));
    System.out.println("pol: " + pol.format(formatter));
    System.out.println("addYears: " + birth.plusYears(personSex.equals("M") ? 65 : 60).format(formatter));
    //System.out.println("birthDayOfYear: " + birthDayOfYear);
    //System.out.println("polDayOfYear: " + polDayOfYear);

    LocalDate result;

    if(pol.getDayOfYear() < birth.getDayOfYear()) {
      result = LocalDate
          .of(birth.plusYears(personSex.equals("M") ? 65 : 60).getYear(), pol.getMonthValue(), pol.getDayOfMonth())
          .plusYears(1L)
          .minusDays(1L);
    } else {
      result = LocalDate
          .of(birth.plusYears(personSex.equals("M") ? 65 : 60).getYear(), pol.getMonthValue(), pol.getDayOfMonth())
          .minusDays(1L);
    }

    System.out.println("result: " + result.format(formatter));

    /*
    personSex: M
    birth: 13.07.1980
    pol: 05.09.2022
    addYears: 13.07.2045
    result: 04.09.2045
    */

    /*
    personSex: F
    birth: 13.07.1980
    pol: 05.09.2022
    addYears: 13.07.2040
    result: 04.09.2040
    */

    /*
    personSex: M
    birth: 13.07.1980
    pol: 05.06.2022
    addYears: 13.07.2045
    result: 04.06.2046
    */

    /*
    personSex: F
    birth: 13.07.1980
    pol: 05.06.2022
    addYears: 13.07.2040
    result: 04.06.2041
    */


  }




  @Test
  void Test6() {
    Gender personSex = Gender.MALE;
    System.out.println("пол: " + personSex);
    System.out.println("пол: " + personSex.name());
  }






}