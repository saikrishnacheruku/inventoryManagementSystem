package com.inventory.controller;


import com.inventory.model.*;
import com.inventory.model.Supplier;
import com.inventory.service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportingController {
    @Autowired
    private ReportingService reportingService;

    @GetMapping("/stock-levels")
    public List<Item> getStockLevels() {
        return reportingService.getStockLevels();
    }

    @GetMapping("/order-statuses")
    public List<PurchaseOrder> getOrderStatuses() {
        return reportingService.getOrderStatuses();
    }

    @GetMapping("/supplier-performance")
    public List<Supplier> getSupplierPerformance() {
        return reportingService.getSupplierPerformance();
    }
}