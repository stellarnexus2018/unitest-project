package ru.master.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.master.api.service.EngineOneService;

@SpringBootTest
class UnitestProjectApplicationTests {
  @Autowired
  private EngineOneService engineOne;

  @Test
  void contextLoads() {
    System.out.println("проверка");


  }
}