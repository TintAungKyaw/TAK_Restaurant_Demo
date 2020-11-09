package com.tak.restaurant.controllers;

import com.tak.restaurant.daos.ConfirmOrderDAO;
import com.tak.restaurant.daos.FoodDrinkDAO;
import com.tak.restaurant.daos.FoodTableDAO;
import com.tak.restaurant.daos.PreOrderDAO;
import com.tak.restaurant.models.ConfirmOrder;
import com.tak.restaurant.models.Food_Drink;
import com.tak.restaurant.models.Food_Table;
import com.tak.restaurant.models.PreOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    FoodTableDAO foodTableDAO;

    @Autowired
    FoodDrinkDAO foodDrinkDAO;

    @Autowired
    PreOrderDAO preOrderDAO;

    @Autowired
    ConfirmOrderDAO confirmOrderDAO;

    @GetMapping("/order")
    public String getOrder(Model model) {
        List<Food_Table> food_table = foodTableDAO.findAll();
        List<PreOrder> preOrder = preOrderDAO.findAll();
        List<ConfirmOrder> confirmOrders = confirmOrderDAO.findAll();
        model.addAttribute("table", food_table);
        model.addAttribute("preorder", preOrder);
        model.addAttribute("cf", confirmOrders);
        model.addAttribute("title", "TAK Restaurant");
        return "order";
    }

    @GetMapping("/orderlist/{id}")
    public String getOrderList(@PathVariable int id, Model model) {
        List<Food_Drink> food_drinks = foodDrinkDAO.findAll();
        Food_Table food_tables = foodTableDAO.findById(id).orElse(null);
        List<PreOrder> preOrder = preOrderDAO.findAll();
        model.addAttribute("order","order");
        model.addAttribute("preorder", preOrder);
        model.addAttribute("table", food_tables);
        model.addAttribute("fd", food_drinks);
        model.addAttribute("title", "TAK Restaurant");
        return "orderlist";
    }

    @PostMapping("/preorder")
    public String getPreOrder(HttpServletRequest request,Model model) {
        model.addAttribute("order","order");
        int tid = Integer.parseInt(request.getParameter("tid"));
        int fid = Integer.parseInt(request.getParameter("fid"));
        int price = Integer.parseInt(request.getParameter("price"));
        int qty = Integer.parseInt(request.getParameter("qty"));
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String cmt = request.getParameter("cmt");
        preOrderDAO.save(new PreOrder(tid, fid, price, qty, 0, name, type, cmt));
        return "redirect:/orderlist/" + tid;
    }

    @GetMapping("/confirmorder/{id}")
    public String getConfirmOrder(@PathVariable int id,Model model) {
        model.addAttribute("order","order");
        List<ConfirmOrder> confirmOrders = confirmOrderDAO.findAll();
        List<PreOrder> preOrders = preOrderDAO.findAll();
        List<Integer> p_ids = new ArrayList<>();
        Food_Table food_table1 = foodTableDAO.findById(id).orElse(null);
        String t_name = food_table1.getName();
        for (PreOrder preOrder : preOrders) {
            if (preOrder.getTid() == id) {
                int qty = preOrder.getAmount();
                int price = preOrder.getF_price();
                int pid = preOrder.getId();
                int amount = qty * price;
                String name = preOrder.getF_name();
                String type = preOrder.getType();
                String cmt = preOrder.getCmt();
                for (ConfirmOrder confirmOrder : confirmOrders) {
                    p_ids.add(confirmOrder.getPid());
                }
                if (confirmOrders.isEmpty() || !p_ids.contains(pid)) {
                    Food_Table food_table = foodTableDAO.findById(id).orElse(null);
                    food_table.setActive("customer");
                    foodTableDAO.save(food_table);
                    confirmOrderDAO.save(new ConfirmOrder(qty, price, pid, 0, 0, amount, id, name, type, cmt, t_name));
                }
            }
        }
        return "redirect:/order";
    }

    @PostMapping("/editamount")
    public String postEditAmount(HttpServletRequest request, Model model) {
        model.addAttribute("order","order");
        int pre_id = Integer.parseInt(request.getParameter("id"));
        int tid = Integer.parseInt(request.getParameter("tid"));
        int amount = Integer.parseInt(request.getParameter("qty"));
        List<Integer> p_ids = new ArrayList<>();
        List<ConfirmOrder> confirmOrders = confirmOrderDAO.findAll();
        for (ConfirmOrder confirmOrder : confirmOrders) {
            p_ids.add(confirmOrder.getPid());
        }
        if (!p_ids.contains(pre_id)) {
            if (amount != 0) {
                PreOrder preOrder = preOrderDAO.findById(pre_id).orElse(null);
                preOrder.setAmount(amount);
                preOrderDAO.save(preOrder);
            } else {
                preOrderDAO.deleteById(pre_id);
            }
        } else {
        }
        return "redirect:/orderlist/" + tid;
    }

    @GetMapping("/cashpay/{id}")
    public String getCashPay(@PathVariable int id) {
        Food_Table food_table = foodTableDAO.findById(id).orElse(null);
        food_table.setActive(null);
        foodTableDAO.save(food_table);
        List<ConfirmOrder> confirmOrders = confirmOrderDAO.findAll();
        for (ConfirmOrder confirmOrder : confirmOrders) {
            if (confirmOrder.getTid() == id) {
                confirmOrder.setPaid_status(1);
                confirmOrder.setC_status(4);
                confirmOrderDAO.save(confirmOrder);
            }
        }
        List<PreOrder> preOrders = preOrderDAO.findAll();
        for (PreOrder preOrder : preOrders) {
            if (preOrder.getTid() == id) {
                preOrder.setPaid_status(1);
                preOrderDAO.save(preOrder);
            }
        }
        return "redirect:/order";
    }

    @PostMapping("/confirmorder")
    public String postConfirmOrder(HttpServletRequest request) {
        int total = Integer.parseInt(request.getParameter("total"));
        System.out.println(total);
        return "order";
    }
}