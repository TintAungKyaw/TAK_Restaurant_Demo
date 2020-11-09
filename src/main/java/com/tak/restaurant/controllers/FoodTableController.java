package com.tak.restaurant.controllers;

import com.tak.restaurant.daos.FoodTableDAO;
import com.tak.restaurant.models.Food_Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class FoodTableController {

    @Autowired
    FoodTableDAO foodTableDAO;

    @GetMapping("/table")
    public String getTable(Model model) {
        List<Food_Table> food_tables = foodTableDAO.findAll();
        model.addAttribute("table","table");
        model.addAttribute("tList", food_tables);
        model.addAttribute("title", "TAK Restaurant");
        return "table";
    }

    @PostMapping("/cft")
    public String postTable(Food_Table food_table) {
        foodTableDAO.save(food_table);
        return "redirect:/table";
    }

    @PostMapping("/edittable")
    public String postEditTable(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Food_Table food_table = foodTableDAO.findById(id).orElse(null);
        food_table.setName(request.getParameter("name"));
        food_table.setStatus(request.getParameter("status"));
        foodTableDAO.save(food_table);
        return "redirect:/table";
    }

    @GetMapping("/deletetable/{id}")
    public String getDeleteTable(@PathVariable int id) {
        foodTableDAO.deleteById(id);
        return "redirect:/table";
    }

    @GetMapping("/status/{id}")
    public String getStatus(@PathVariable int id) {
        Food_Table food_table = foodTableDAO.findById(id).orElse(null);
        if (food_table.getStatus().contentEquals("enable")) {
            food_table.setStatus("disable");
        } else {
            food_table.setStatus("enable");
        }
        foodTableDAO.save(food_table);
        return "redirect:/table";
    }
}