package com.tak.restaurant.controllers;

import com.tak.restaurant.daos.ConfirmOrderDAO;
import com.tak.restaurant.models.ConfirmOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class WaiterController {

    @Autowired
    ConfirmOrderDAO confirmOrderDAO;

    @GetMapping("/waiter")
    public String getWaiter(Model model) {
        List<ConfirmOrder> confirmOrders = confirmOrderDAO.findAll();
        model.addAttribute("co", confirmOrders);
        model.addAttribute("title", "TAK Restaurant");
        return "waiter";
    }

    @GetMapping("/takesent/{id}")
    public String getTakeSend(@PathVariable int id) {
        List<ConfirmOrder> confirmOrders = confirmOrderDAO.findAll();
        for (ConfirmOrder confirmOrder : confirmOrders) {
            if (confirmOrder.getId() == id) {
                confirmOrder.setC_status(3);
                confirmOrderDAO.save(confirmOrder);
            }
        }
        return "redirect:/waiter";
    }
}
