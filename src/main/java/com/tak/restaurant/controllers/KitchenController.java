package com.tak.restaurant.controllers;

import com.tak.restaurant.daos.ConfirmOrderDAO;
import com.tak.restaurant.daos.FoodTableDAO;
import com.tak.restaurant.models.ConfirmOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class KitchenController {

    @Autowired
    ConfirmOrderDAO confirmOrderDAO;

    @Autowired
    FoodTableDAO foodTableDAO;

    @GetMapping("/kitchen")
    public String getKitchen(Model model) {
        List<ConfirmOrder> confirmOrders = confirmOrderDAO.findAll();
        model.addAttribute("co", confirmOrders);
        model.addAttribute("title", "TAK Restaurant");
        return "kitchen";
    }

    @GetMapping("/neworder/{id}")
    public String getNewOrder(@PathVariable int id) {
        List<ConfirmOrder> confirmOrders = confirmOrderDAO.findAll();
        for (ConfirmOrder confirmOrder : confirmOrders) {
            if (confirmOrder.getId() == id) {
                confirmOrder.setC_status(1);
                confirmOrderDAO.save(confirmOrder);
            }
        }
        return "redirect:/kitchen";
    }

    @GetMapping("/cook/{id}")
    public String getCook(@PathVariable int id) {
        List<ConfirmOrder> confirmOrders = confirmOrderDAO.findAll();
        for (ConfirmOrder confirmOrder : confirmOrders) {
            if (confirmOrder.getId() == id) {
                confirmOrder.setC_status(2);
                confirmOrderDAO.save(confirmOrder);
            }
        }
        return "redirect:/kitchen";
    }
}
