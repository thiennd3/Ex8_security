package com.sapo.ex8_spring_security.controller;

import com.sapo.ex8_spring_security.sevice.CategoryService;
import com.sapo.ex8_spring_security.sevice.ProductService;
import com.sapo.ex8_spring_security.sevice.StorageService;
import com.sapo.ex8_spring_security.shared.Result;
import com.sapo.ex8_spring_security.model.entity.Product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController  {


    ProductService productService;
    CategoryService categoryService;
    StorageService storageService;

    public ProductController(ProductService productService, CategoryService categoryService, StorageService storageService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.storageService = storageService;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new Result("Fail", "wrong input", errors);
    }

    @GetMapping("products")
    public List<Product> getAll(@RequestParam String name, @RequestParam Integer page, @RequestParam Integer size) {
        return productService.getAll(name, page - 1, size);

    }



    @PostMapping("product")
    public Object add(@RequestBody @Valid Product body) {
        try {

            return productService.add(body);
        } catch (Exception e) {
            return new Result("Fail", e.getMessage(), body);
        }
    }

    @PutMapping("product/{Id}")
    public Object update(@PathVariable Integer Id, @RequestBody @Valid Product body) {
        try {
            return productService.update(Id,body);

        } catch (Exception e) {
            return new Result<>("Fail", e.getMessage(), body);
        }

    }

    @DeleteMapping("product/{Id}")
    public Object delete(@PathVariable Integer Id) {
        try {
            productService.delete(Id);
            return Id;
        } catch (Exception e) {
            return new Result<>("Fail", e.getMessage(), Id);
        }

    }


}
