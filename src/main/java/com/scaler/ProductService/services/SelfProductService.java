package com.scaler.ProductService.services;

import com.scaler.ProductService.exceptions.ProductNotFoundException;
import com.scaler.ProductService.models.Category;
import com.scaler.ProductService.models.Product;
import com.scaler.ProductService.repositories.CategoryRepository;
import com.scaler.ProductService.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
       //make a call to db to fetch a product with given id
       Optional<Product> productOptional = productRepository.findById(productId);

       if(productOptional.isEmpty()) {
           throw new ProductNotFoundException("Product with id " + productId + " not found");
       }

       return productOptional.get();
    }

    @Override
//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }
    public Page<Product> getAllProducts(int pageNumber, int pageSize) {
       // Sort sort = Sort.by("price").ascending().and(Sort.by("title").descending())
       // Sort.by("price").ascending().and(Sort.by("title").ascending().and(Sort.by("quantity").ascending()
        return productRepository.findAll(
                PageRequest.of(pageNumber,
                        pageSize,
                        Sort.by("price").ascending())
        );

    }


    //PATCH
    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException{
       Optional<Product> optionalProduct = productRepository.findById(id);

       if(optionalProduct.isEmpty()) {
           throw new ProductNotFoundException("Product with id :" + id + "does not exist");
       }
       Product productInDB = optionalProduct.get();

       if(productInDB.getTitle() != null) {
           productInDB.setTitle(product.getTitle());
       }

       if(productInDB.getPrice() != null){
           productInDB.setPrice(product.getPrice());
       }
       return productRepository.save(productInDB);
    }

    //PUT
    //ToDo Task
    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product addProduct(Product product) {
        Category category = product.getCategory();
        //using cascade it is not required.
        //check if category.Id exist or not if not first create new category then new product inserted
//        if(category.getId() == null) {
//            //we need to create a new category object in the DB first
//            category = categoryRepository.save(category);
//            product.setCategory(category);
//        }
        return productRepository.save(product);
    }
}
