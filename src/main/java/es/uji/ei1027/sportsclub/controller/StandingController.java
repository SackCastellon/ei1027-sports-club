package es.uji.ei1027.sportsclub.controller;

import es.uji.ei1027.sportsclub.model.Swimmer;
import es.uji.ei1027.sportsclub.services.IClassificationService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/standing")
@SuppressWarnings({"HardcodedFileSeparator", "FieldHasSetterButNoGetter"})
public class StandingController {

    private IClassificationService service;

    @Autowired
    public final void setService(@NotNull IClassificationService service) {
        this.service = service;
    }

    @RequestMapping(path = "/byCountry/{event}", method = RequestMethod.GET)
    public final String listClassificationByCountry(@NotNull Model model, @PathVariable("event") @NotNull String event) {
        final @NotNull Map<String, List<Swimmer>> standings = service.getClassificationByCountry(event);
        if (standings.isEmpty()) return "redirect:/event/list";

        model.addAttribute("eventName", event);
        model.addAttribute("standings", standings);
        return "standing/byCountry";
    }

    @RequestMapping(path = "/byEvent/{country}", method = RequestMethod.GET)
    public final String listClassificationByEvent(@NotNull Model model, @PathVariable("country") @NotNull String country) {
        final @NotNull Map<String, List<Swimmer>> standings = service.getClassificationByEvent(country);
        if (standings.isEmpty()) return "redirect:/event/list";

        model.addAttribute("countryName", country);
        model.addAttribute("standings", standings);
        return "standing/byEvent";
    }

    @Override
    public final @NotNull String toString() {
        return String.format("StandingController{service=%s}", service);
    }
}
