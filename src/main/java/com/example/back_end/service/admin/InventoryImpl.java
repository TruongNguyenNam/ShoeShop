package com.example.back_end.service.admin;

import com.example.back_end.dto.response.InventoryResponse;
import com.example.back_end.entity.Inventory;
import com.example.back_end.exception.AppException;
import com.example.back_end.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryImpl implements InventoryService{
    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<InventoryResponse> getALL() {
      List<Inventory> inventories = inventoryRepository.findAll();
      if(inventories.isEmpty()){
          throw new AppException("inventories is not found");
      }
      return inventories.stream().map(this::mapToResponse).collect(Collectors.toList());


    }

    private InventoryResponse mapToResponse(Inventory inventory){
            InventoryResponse response = new InventoryResponse();
            response.setId(inventory.getId());
            response.setProductId(inventory.getProduct().getId());
            response.setProductName(inventory.getProduct().getName());
            response.setStock(inventory.getStock());
            return response;
    }
}
