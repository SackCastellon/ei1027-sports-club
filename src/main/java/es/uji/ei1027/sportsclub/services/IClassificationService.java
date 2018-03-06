package es.uji.ei1027.sportsclub.services;

import es.uji.ei1027.sportsclub.model.Swimmer;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public interface IClassificationService {

    @NotNull Map<String, List<Swimmer>> getClassificationByCountry(@NotNull String event);

    @NotNull Map<String, List<Swimmer>> getClassificationByEvent(@NotNull String country);
}
