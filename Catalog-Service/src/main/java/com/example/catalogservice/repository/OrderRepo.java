package com.example.catalogservice.repository;

import com.example.catalogservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order,Integer> {

    List<Order> findByCategory(String category);
}
