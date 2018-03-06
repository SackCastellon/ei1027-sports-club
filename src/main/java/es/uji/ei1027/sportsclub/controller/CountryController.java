package es.uji.ei1027.sportsclub.controller;

import es.uji.ei1027.sportsclub.dao.IStandingDao;
import es.uji.ei1027.sportsclub.dao.ISwimmerDao;
import es.uji.ei1027.sportsclub.model.Standing;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/country")
@SuppressWarnings({"HardcodedFileSeparator", "FieldHasSetterButNoGetter"})
public class CountryController {

    private ISwimmerDao swimmerDao;
    private IStandingDao standingDao;

    @Autowired
    public final void setSwimmerDao(@NotNull ISwimmerDao swimmerDao) {
        this.swimmerDao = swimmerDao;
    }

    @Autowired
    public final void setStandingDao(@NotNull IStandingDao standingDao) {
        this.standingDao = standingDao;
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public final @NotNull String list(@NotNull Model model) {
        Set<String> swimmers = standingDao.getStandings().parallelStream()
                .map(Standing::getSwimmerName).distinct().collect(Collectors.toSet());
        List<Map.Entry<String, Boolean>> countries = swimmerDao.getSwimmers().parallelStream()
                .map(it -> new AbstractMap.SimpleEntry<>(it.getCountry(), swimmers.contains(it.getName()))).distinct().collect(Collectors.toList());

        model.addAttribute("countries", countries);
        return "/country/list";
    }

    @Override
    public final @NotNull String toString() {
        return String.format("CountryController{swimmerDao=%s, standingDao=%s}", swimmerDao, standingDao);
    }
}
