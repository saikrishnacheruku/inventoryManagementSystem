package com.inventory.service;

import com.inventory.model.Item;
import com.inventory.model.PurchaseOrder;
import com.inventory.repository.ItemRepository;
import com.inventory.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PurchaseOrderService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private ItemRepository itemRepository;

    public List<PurchaseOrder> getAllOrders() {
        return purchaseOrderRepository.findAll();
    }

    public PurchaseOrder getOrderById(Long id) {
        return purchaseOrderRepository.findById(id).orElse(null);
    }

    public PurchaseOrder placeOrder(PurchaseOrder purchaseOrder) {
        Item item = itemRepository.findById(purchaseOrder.getItemId()).orElse(null);
        if (item != null && item.getQuantity() >= purchaseOrder.getQuantity()) {
            item.setQuantity(item.getQuantity() - purchaseOrder.getQuantity());
            itemRepository.save(item);

            purchaseOrder.setOrderDate(new Date());
            purchaseOrder.setStatus("CONFIRMED");
            return purchaseOrderRepository.save(purchaseOrder);
        }
        return null;
    }

    public PurchaseOrder updateOrder(Long id, PurchaseOrder purchaseOrderDetails) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id).orElse(null);
        if (purchaseOrder != null) {
            purchaseOrder.setQuantity(purchaseOrderDetails.getQuantity());
            purchaseOrder.setStatus(purchaseOrderDetails.getStatus());
            return purchaseOrderRepository.save(purchaseOrder);
        }
        return null;
    }

    public void cancelOrder(Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id).orElse(null);
        if (purchaseOrder != null) {
            purchaseOrder.setStatus("CANCELED");
            purchaseOrderRepository.save(purchaseOrder);

            Item item = itemRepository.findById(purchaseOrder.getItemId()).orElse(null);
            if (item != null) {
                item.setQuantity(item.getQuantity() + purchaseOrder.getQuantity());
                itemRepository.save(item);
            }
        }
    }
}
