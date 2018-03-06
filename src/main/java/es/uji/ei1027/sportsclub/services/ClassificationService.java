package es.uji.ei1027.sportsclub.services;

import es.uji.ei1027.sportsclub.dao.StandingDao;
import es.uji.ei1027.sportsclub.dao.SwimmerDao;
import es.uji.ei1027.sportsclub.model.Standing;
import es.uji.ei1027.sportsclub.model.Swimmer;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassificationService implements IClassificationService {

    @SuppressWarnings("FieldHasSetterButNoGetter")
    private SwimmerDao swimmerDao;
    @SuppressWarnings("FieldHasSetterButNoGetter")
    private StandingDao standingDao;

    @Autowired
    public final void setSwimmerDao(@NotNull SwimmerDao swimmerDao) {
        this.swimmerDao = swimmerDao;
    }

    @Autowired
    public final void setStandingDao(@NotNull StandingDao standingDao) {
        this.standingDao = standingDao;
    }

    @Override
    @SuppressWarnings("DesignForExtension")
    public @NotNull Map<String, List<Swimmer>> getClassificationByCountry(@NotNull String event) {
        final List<Standing> eventStandings = standingDao.getEventStandings(event);
        final Map<String, List<Swimmer>> swimmersByCountry = new HashMap<>();

        for (Standing standing : eventStandings) {
            Swimmer swimmer = swimmerDao.getSwimmer(standing.getSwimmerName());
            swimmersByCountry.computeIfAbsent(swimmer.getCountry(), key -> new ArrayList<>()).add(swimmer);
        }

        return swimmersByCountry;
    }

    @Override
    @SuppressWarnings("DesignForExtension")
    public @NotNull Map<String, List<Swimmer>> getClassificationByEvent(@NotNull String country) {
        final List<Standing> countryStandings = standingDao.getCountryStandings(country);
        final Map<String, List<Swimmer>> swimmersByCountry = new HashMap<>();

        for (Standing standing : countryStandings) {
            Swimmer swimmer = swimmerDao.getSwimmer(standing.getSwimmerName());
            swimmersByCountry.computeIfAbsent(standing.getEventName(), key -> new ArrayList<>()).add(swimmer);
        }

        return swimmersByCountry;
    }

    @Override
    public final @NotNull String toString() {
        return String.format("ClassificationService{swimmerDao=%s, standingDao=%s}", swimmerDao, standingDao);
    }
}
