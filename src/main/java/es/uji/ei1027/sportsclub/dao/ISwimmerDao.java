package es.uji.ei1027.sportsclub.dao;

import es.uji.ei1027.sportsclub.model.Swimmer;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ISwimmerDao {

    @NotNull List<Swimmer> getSwimmers();

    @NotNull Swimmer getSwimmer(@NotNull String name);

    void addSwimmer(@NotNull Swimmer swimmer);

    void updateSwimmer(@NotNull Swimmer swimmer);

    void deleteSwimmer(@NotNull String name);
}
