package com.example.controllers;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.TenantContext;
import com.example.domain.Order;
import com.example.domain.OrderRepository;

@Controller
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(path = "/orders", method= RequestMethod.POST)
    public ResponseEntity<?> createSampleOrder(@RequestHeader("X-TenantID") String tenantName) {
        TenantContext.getInstance().setCurrentTenant(tenantName);
        Order newOrder = new Order();
        newOrder.setDate(new Date(System.currentTimeMillis()));
        orderRepository.save(newOrder);

        return ResponseEntity.ok(newOrder);
    }
    @RequestMapping(path = "/orders", method= RequestMethod.GET)
    public ResponseEntity<?> getSampleOrder(@RequestHeader("X-TenantID") String tenantName) {
    	TenantContext.getInstance().setCurrentTenant(tenantName);
        return ResponseEntity.ok(orderRepository.findAll());
    }
}
