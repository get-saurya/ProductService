package com.scaler.ProductService.repositories;

import com.scaler.ProductService.models.Product;
import com.scaler.ProductService.projections.ProductWithIdAndTitle;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long > {
    //Product Repo should contain all the methods(CRUD) related to product model.


   Optional<Product> findById(Long id);

   @Override
   List<Product> findAll(Sort sort);

   //HQL
   @Query("select p.id as id, p.title as title from Product p where p.id = 1")
  // void randomSearchMethod();
   ProductWithIdAndTitle randomSearchMethod();
   //HQL
   @Query("select p.id as id, p.title as title from Product p where p.id = :x")
   List<ProductWithIdAndTitle> randomSearchMethod(Long x);

   //SQL
   @Query(value = "select p.id as id, p.title as title from product p where p.id = :productId", nativeQuery = true)
   List<ProductWithIdAndTitle> randomSearchMethod2(Long productId);

}

/*
1. Repository should be an interface
2. Repository should extend JPARepository
* */