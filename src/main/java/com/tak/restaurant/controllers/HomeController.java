package com.tak.restaurant.controllers;

import com.tak.restaurant.daos.RegisterDAO;
import com.tak.restaurant.daos.ShopRulesDAO;
import com.tak.restaurant.models.Register;
import com.tak.restaurant.models.ShopRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    RegisterDAO registerDAO;

    @Autowired
    ShopRulesDAO shopRulesDAO;

    @GetMapping("/home")
    public String getHome(Model model) {
        List<ShopRules> shopRules = shopRulesDAO.findAll();
        model.addAttribute("home","home");
        model.addAttribute("rule", shopRules);
        model.addAttribute("title", "TAK Restaurant");
        return "home";
    }

    @PostMapping("/home")
    public String postHome(HttpServletRequest request, Model model) {
        String page = "index";
        model.addAttribute("home","home");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        List<Register> getDB = registerDAO.findAll();
        for (Register check : getDB) {
            if (check.getEmail().equals(email) && check.getPassword().equals(password)) {
                page = "home";
            }
        }
        model.addAttribute("title", "TAK Restaurant");
        return page;
    }
}
