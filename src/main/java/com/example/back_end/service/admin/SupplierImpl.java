package com.example.back_end.service.admin;

import com.example.back_end.dto.admin.response.SupplierResponse;
import com.example.back_end.entity.Supplier;
import com.example.back_end.exception.AppException;
import com.example.back_end.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierImpl implements SupplierService{
        private final SupplierRepository supplierRepository;
    @Autowired
    public SupplierImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<SupplierResponse> getAll() {
        List<Supplier> suppliers = supplierRepository.findAll();
        if(suppliers.isEmpty()){
            throw new AppException("suppliers is not found");
        }
        return suppliers.stream().map(this::mapToResponse).collect(Collectors.toList());


    }

    private SupplierResponse mapToResponse(Supplier supplier){
        SupplierResponse response = new SupplierResponse();
        response.setId(supplier.getId());
        response.setName(supplier.getName());
        response.setContactInfo(supplier.getContactInfo());
        return response;
    }
}
