package com.example.appWithReact.service;

import com.example.appWithReact.model.ProductItem;
import com.example.appWithReact.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProducctService {

    @Autowired
    private ProductRepository repo;

    public List<ProductItem> getProducts() {
        return repo.findAll();
    }

    public ProductItem getProductById(int id) {
        return repo.findById(id).orElse(new ProductItem());
    }

    public ProductItem addNewProduct(ProductItem product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repo.save(product);
    }


    public ProductItem updateProduct(int productId, ProductItem product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repo.save(product);
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public List<ProductItem> searchForKeyword(String keyword) {
        return repo.searchProductsByKeyword(keyword);
    }
}
