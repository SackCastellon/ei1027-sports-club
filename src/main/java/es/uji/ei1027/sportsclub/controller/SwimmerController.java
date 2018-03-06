package es.uji.ei1027.sportsclub.controller;

import es.uji.ei1027.sportsclub.dao.ISwimmerDao;
import es.uji.ei1027.sportsclub.model.Swimmer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/swimmer")
@SuppressWarnings({"HardcodedFileSeparator", "FieldHasSetterButNoGetter"})
public class SwimmerController {

    private final @NotNull Logger log = LoggerFactory.getLogger(getClass());

    private ISwimmerDao dao;

    @Autowired
    public final void setDao(@NotNull ISwimmerDao dao) {
        this.dao = dao;
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public final @NotNull String list(@NotNull Model model) {
        model.addAttribute("swimmers", dao.getSwimmers());
        return "/swimmer/list";
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public final @NotNull String add(@NotNull Model model) {
        model.addAttribute("swimmer", new Swimmer());
        return "/swimmer/add";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public final @NotNull String processAddSubmit(@NotNull BindingResult bindingResult, @ModelAttribute("swimmer") @NotNull Swimmer swimmer) {
        if (bindingResult.hasErrors()) return "swimmer/add";
        try {
            dao.addSwimmer(swimmer);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
        return "redirect:list";
    }

    @RequestMapping(path = "/update/{name}", method = RequestMethod.GET)
    public final @NotNull String update(@NotNull Model model, @PathVariable("name") @NotNull String name) {
        try {
            model.addAttribute("swimmer", dao.getSwimmer(name));
            return "/swimmer/update";
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
        return "redirect:../list";
    }

    @RequestMapping(path = "/update/{name}", method = RequestMethod.POST)
    public final @NotNull String processUpdateSubmit(@PathVariable("name") @NotNull String name, @ModelAttribute("swimmer") @NotNull Swimmer swimmer, @NotNull BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "swimmer/update";
        dao.updateSwimmer(swimmer);
        return "redirect:../list";
    }


    @RequestMapping(path = "/delete/{name}", method = RequestMethod.GET)
    public final @NotNull String processDelete(@PathVariable("name") @NotNull String name) {
        dao.deleteSwimmer(name);
        return "redirect:../list";
    }

    @Override
    public final @NotNull String toString() {
        return String.format("SwimmerController{dao=%s}", dao);
    }
}
