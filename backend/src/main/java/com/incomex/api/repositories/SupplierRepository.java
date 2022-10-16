package com.incomex.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incomex.api.entities.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer>{

}
