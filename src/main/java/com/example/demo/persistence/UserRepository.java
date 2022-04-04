package com.example.demo.persistence;

import com.example.demo.model.Product;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<Product, Integer> {

}
