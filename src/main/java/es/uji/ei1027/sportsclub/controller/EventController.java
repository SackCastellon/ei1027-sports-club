package es.uji.ei1027.sportsclub.controller;

import es.uji.ei1027.sportsclub.dao.IEventDao;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/event")
@SuppressWarnings("HardcodedFileSeparator")
public class EventController {

    @SuppressWarnings("FieldHasSetterButNoGetter")
    private IEventDao dao;

    @Autowired
    public final void setDao(@NotNull IEventDao dao) {
        this.dao = dao;
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public final @NotNull String list(@NotNull Model model) {
        model.addAttribute("events", dao.getEvents());
        return "/event/list";
    }

    @Override
    public final @NotNull String toString() {
        return String.format("EventController{dao=%s}", dao);
    }
}
