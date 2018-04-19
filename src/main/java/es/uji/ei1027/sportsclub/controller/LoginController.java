package es.uji.ei1027.sportsclub.controller;

import es.uji.ei1027.sportsclub.dao.IUserDao;
import es.uji.ei1027.sportsclub.model.UserDetails;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {

    private IUserDao dao;

    @Autowired
    public final void setDao(@NotNull IUserDao dao) {
        this.dao = dao;
    }

    @RequestMapping("/login")
    public final @NotNull String login(final @NotNull Model model) {
        model.addAttribute("user", new UserDetails());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public final @NotNull String checkLogin(@ModelAttribute("user") final @NotNull UserDetails user, final @NotNull BindingResult bindingResult, final @NotNull HttpSession session) {
        UserValidator userValidator = new UserValidator();
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) return "login";

        Optional<UserDetails> details = dao.loadUserByUsername(user.getUsername(), user.getPassword());

        if (details.isPresent()) {
            session.setAttribute("user", details.get());
            String nextUrl = (String) session.getAttribute("nextUrl");
            session.removeAttribute("nextUrl");
            return "redirect:" + ((nextUrl == null) ? "/" : nextUrl);
        } else {
            bindingResult.rejectValue("password", "badpw", "Incorrect password");
            return "login";
        }
    }

    @RequestMapping("/logout")
    public final @NotNull String logout(final @NotNull HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
