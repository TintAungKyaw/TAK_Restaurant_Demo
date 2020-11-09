package com.tak.restaurant.controllers;

import com.tak.restaurant.daos.FoodDrinkDAO;
import com.tak.restaurant.models.Food_Drink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class FoodController {

    @Autowired
    FoodDrinkDAO foodDrinkDAO;

    @GetMapping("/food")
    public String getFood(Model model) {
        List<Food_Drink> food_drinks = foodDrinkDAO.findAll();
        model.addAttribute("food", "food");
        model.addAttribute("fList", food_drinks);
        model.addAttribute("title", "TAK Restaurant");
        return "food";
    }

    @PostMapping("/cf")
    public String postFood(Food_Drink foodDrink) {
        foodDrinkDAO.save(foodDrink);
        return "redirect:/food";
    }

    @GetMapping("/foodstatus/{id}")
    public String getFoodStatus(@PathVariable int id) {
        Food_Drink food_drink = foodDrinkDAO.findById(id).orElse(null);
        if (food_drink.getStatus().contentEquals("enable")) {
            food_drink.setStatus("disable");
        } else {
            food_drink.setStatus("enable");
        }
        foodDrinkDAO.save(food_drink);
        return "redirect:/food";
    }

    @PostMapping("/editfood")
    public String postEditTable(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Food_Drink food_drink = foodDrinkDAO.findById(id).orElse(null);
        food_drink.setName(request.getParameter("name"));
        food_drink.setPrice(Integer.parseInt(request.getParameter("price")));
        food_drink.setStatus(request.getParameter("status"));
        foodDrinkDAO.save(food_drink);
        return "redirect:/food";
    }

    @GetMapping("/deletefood/{id}")
    public String getDeleteTable(@PathVariable int id) {
        foodDrinkDAO.deleteById(id);
        return "redirect:/food";
    }
}
