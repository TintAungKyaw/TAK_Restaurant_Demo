package com.tak.restaurant.controllers;

import com.tak.restaurant.daos.ShopRulesDAO;
import com.tak.restaurant.models.ShopRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class RuleController {

    @Autowired
    ShopRulesDAO shopRulesDAO;

    @GetMapping("/rule")
    public String getRule(Model model) {
        List<ShopRules> shopRules = shopRulesDAO.findAll();
        model.addAttribute("navRule","rule");
        model.addAttribute("rule", shopRules);
        model.addAttribute("title", "TAK Restaurant");
        return "rule";
    }

    @PostMapping("/cr")
    public String postCreateRule(HttpServletRequest request) {
        String rule = request.getParameter("rule");
        String status = request.getParameter("status");
        shopRulesDAO.save(new ShopRules(rule, status));
        return "redirect:/rule";
    }

    @GetMapping("/rulestatus/{id}")
    public String getRuleStatus(@PathVariable int id) {
        ShopRules shopRules = shopRulesDAO.findById(id).orElse(null);
        if (shopRules.getStatus().contentEquals("enable")) {
            shopRules.setStatus("disable");
        } else {
            shopRules.setStatus("enable");
        }
        shopRulesDAO.save(shopRules);
        return "redirect:/rule";
    }

    @PostMapping("editrule")
    public String postEditRule(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String rule = request.getParameter("rule");
        String status = request.getParameter("status");
        ShopRules shopRules = shopRulesDAO.findById(id).orElse(null);
        shopRules.setRule(rule);
        shopRules.setStatus(status);
        shopRulesDAO.save(shopRules);
        return "redirect:/rule";
    }

    @GetMapping("/deleterule/{id}")
    public String getDeleteRule(@PathVariable int id) {
        shopRulesDAO.deleteById(id);
        return "redirect:/rule";
    }
}
