package com.example.appWithReact.repo;

import com.example.appWithReact.model.ProductItem;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductItem,Integer> {
    @Query("SELECT p from ProductItem p where" +
            " LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR" +
            " LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR"  +
            " LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR" +
            " LOWER(p.category) LIKE LOWER( CONCAT('%', :keyword, '%'))"
            )
    List<ProductItem> searchProductsByKeyword(String keyword);

}
