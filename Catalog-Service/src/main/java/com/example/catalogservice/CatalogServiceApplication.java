package com.example.catalogservice;

import com.example.catalogservice.entity.Order;
import com.example.catalogservice.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
@RequestMapping("/order")
public class CatalogServiceApplication {

    @Autowired
    private OrderRepo orderRepo;
    @PostConstruct
    public void initOrderTable(){
        orderRepo.saveAll(Stream.of(
                new Order("mobile", "electronics", "white", 20000),
                new Order("T-Shirt", "clothes", "black", 999),
                new Order("Jeans", "clothes", "blue", 1999),
                new Order("Laptop", "electronics", "gray", 50000),
                new Order("digital watch", "electronics", "black", 2500),
                new Order("Fan", "electronics", "black", 50000)
        ).collect(Collectors.toList()));
    }

    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders(){
        return orderRepo.findAll();

    }

    @GetMapping("/getOrder/{id}")
    public Optional<Order> getOrderById(@PathVariable int id ){
        return orderRepo.findById(id);

    }

    @GetMapping("/getOrder/{category}")
    public List<Order> getOrderByCategory(@PathVariable String category){
        return orderRepo.findByCategory(category);

    }

    public static void main(String[] args) {
        SpringApplication.run(CatalogServiceApplication.class, args);
    }

}
