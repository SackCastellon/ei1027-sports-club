package es.uji.ei1027.sportsclub.controller;

import es.uji.ei1027.sportsclub.dao.IUserDao;
import es.uji.ei1027.sportsclub.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    private IUserDao dao;

    @Autowired
    public void setPartnerDao(IUserDao dao) {
        this.dao = dao;
    }

    @RequestMapping("/list")
    public String listPartners(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            model.addAttribute("user", new UserDetails());
            session.setAttribute("nextUrl", "/user/list");
            return "/login";
        }
        model.addAttribute("users", dao.listAllUsers());
        return "/user/list";
    }
}
