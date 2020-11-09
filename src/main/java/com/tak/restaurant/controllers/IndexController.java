package com.tak.restaurant.controllers;

import com.tak.restaurant.daos.RegisterDAO;
import com.tak.restaurant.models.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class IndexController {

    @Autowired
    RegisterDAO registerDAO;

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("index","index");
        model.addAttribute("title", "TAK Restaurant");
        return "index";
    }

    @PostMapping("/")
    public String postIndex(HttpServletRequest request, Model model) {
        model.addAttribute("index","index");
        String name = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String re_password = request.getParameter("re_password");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        if (password.contentEquals(re_password)) {
            registerDAO.save(new Register(name, email, password, gender, birthday));
            model.addAttribute("RegMsg", "0");
        } else {
            model.addAttribute("RegMsg", "1");
        }
        return "index";
    }

    @PostMapping("/reset")
    public String postReset(HttpServletRequest request, Model model) {
        model.addAttribute("index","index");
        String name = request.getParameter("username");
        String birthday = request.getParameter("birthday");
        List<Register> registers = registerDAO.findAll();
        for (Register check : registers) {
            if (check.getName().contentEquals(name) && check.getBirthday().contentEquals(birthday)) {
                model.addAttribute("email", "Email : " + check.getEmail());
                model.addAttribute("password", "Password : " + check.getPassword());
            } else {
                model.addAttribute("reset", "0");
            }
        }
        return "index";
    }
}