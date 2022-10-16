package com.incomex.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incomex.api.entities.Supplier;
import com.incomex.api.repositories.SupplierRepository;

@Service
public class SupplierService {
	@Autowired
	private SupplierRepository supplierRepository;
	
	public Supplier findById(Integer supplierId) {
		Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);
		if(optionalSupplier.isPresent()) {
			return optionalSupplier.get();
		}
		return null;
	}

}
