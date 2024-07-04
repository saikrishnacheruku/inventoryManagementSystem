package com.inventory.service;


import com.inventory.model.*;
import com.inventory.model.Supplier;
import com.inventory.repository.ItemRepository;
import com.inventory.repository.PurchaseOrderRepository;
import com.inventory.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportingService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Item> getStockLevels() {
        return itemRepository.findAll();
    }

    public List<PurchaseOrder> getOrderStatuses() {
        return purchaseOrderRepository.findAll();
    }

    public List<Supplier> getSupplierPerformance() {
        return supplierRepository.findAll();
    }
}