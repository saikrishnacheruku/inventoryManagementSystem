package com.inventory.controller;

import com.inventory.model.PurchaseOrder;
import com.inventory.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping
    public List<PurchaseOrder> getAllOrders() {
        return purchaseOrderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrder> getOrderById(@PathVariable Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderService.getOrderById(id);
        return purchaseOrder != null ? ResponseEntity.ok(purchaseOrder) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public PurchaseOrder placeOrder(@RequestBody PurchaseOrder purchaseOrder) {
        return purchaseOrderService.placeOrder(purchaseOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseOrder> updateOrder(@PathVariable Long id, @RequestBody PurchaseOrder purchaseOrderDetails) {
        PurchaseOrder updatedOrder = purchaseOrderService.updateOrder(id, purchaseOrderDetails);
        return updatedOrder != null ? ResponseEntity.ok(updatedOrder) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long id) {
        purchaseOrderService.cancelOrder(id);
        return ResponseEntity.noContent().build();
    }
}
