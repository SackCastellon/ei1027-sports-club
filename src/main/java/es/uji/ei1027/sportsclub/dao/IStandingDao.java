package es.uji.ei1027.sportsclub.dao;

import es.uji.ei1027.sportsclub.model.Standing;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IStandingDao {

    @NotNull List<Standing> getStandings();

    @NotNull List<Standing> getEventStandings(@NotNull String eventName);

    @NotNull List<Standing> getCountryStandings(@NotNull String country);
}
