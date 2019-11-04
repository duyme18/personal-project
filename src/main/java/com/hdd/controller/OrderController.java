package com.hdd.controller;

import com.hdd.model.Customer;
import com.hdd.model.Order;
import com.hdd.service.CustomerService;
import com.hdd.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list-order")
    public ModelAndView listProvinces() {
        Iterable<Order> orders = orderService.findAll();
        ModelAndView modelAndView = new ModelAndView("/order/list");
        modelAndView.addObject("orders", orders);
        return modelAndView;
    }

    @GetMapping("/create-order")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/order/create");
        modelAndView.addObject("order", new Order());
        return modelAndView;
    }

    @PostMapping("/create-order")
    public ModelAndView saveOrder(@ModelAttribute("order") Order order) {
        orderService.save(order);

        ModelAndView modelAndView = new ModelAndView("/order/create");
        modelAndView.addObject("order", new Order());
        modelAndView.addObject("message", "New order created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-order/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Order order = orderService.findById(id);
        if (order != null) {
            ModelAndView modelAndView = new ModelAndView("/order/edit");
            modelAndView.addObject("order", order);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-order")
    public ModelAndView updateOrder(@ModelAttribute("order") Order order) {
        orderService.save(order);
        ModelAndView modelAndView = new ModelAndView("/order/edit");
        modelAndView.addObject("order", order);
        modelAndView.addObject("message", "Order updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-order/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Order order = orderService.findById(id);
        if (order != null) {
            ModelAndView modelAndView = new ModelAndView("/order/delete");
            modelAndView.addObject("order", order);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-order")
    public String deleteProvince(@ModelAttribute("order") Order order) {
        orderService.remove(order.getId());
        return "redirect:list-order";
    }

    @GetMapping("/view-order/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id) {
        Order order = orderService.findById(id);
        if (order == null) {
            return new ModelAndView("/error.404");
        }

        Iterable<Customer> customers = customerService.findAllByOrder(order);

        ModelAndView modelAndView = new ModelAndView("/order/view");
        modelAndView.addObject("order", order);
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

}
