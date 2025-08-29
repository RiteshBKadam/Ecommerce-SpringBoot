package com.example.appWithReact.repo;

import com.example.appWithReact.model.ProductItem;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductItem,Integer> {
}
