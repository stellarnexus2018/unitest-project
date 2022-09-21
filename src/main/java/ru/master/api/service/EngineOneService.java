package ru.master.api.service;

import lombok.NonNull;
import ru.master.api.dto.PersonDto;

import java.util.List;

public interface EngineOneService {
  @NonNull
  String enrollProcess(@NonNull List<String> names, @NonNull List<PersonDto> persons);

  @NonNull
  List<String> enrollCrossing(@NonNull List<String> names, @NonNull List<PersonDto> persons);
}
