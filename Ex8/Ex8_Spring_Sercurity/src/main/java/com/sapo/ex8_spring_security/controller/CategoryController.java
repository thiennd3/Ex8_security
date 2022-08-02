package com.sapo.ex8_spring_security.controller;

import com.sapo.ex8_spring_security.model.dto.request.CategoryRequest;
import com.sapo.ex8_spring_security.model.dto.respond.CategoryRespond;
import com.sapo.ex8_spring_security.sevice.CategoryService;
import com.sapo.ex8_spring_security.shared.Result;
import com.sapo.ex8_spring_security.model.entity.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@RestController
public class CategoryController {
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
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


    @GetMapping("categories")
    public ResponseEntity getAll(@RequestParam String name, @RequestParam @Valid Integer page, @RequestParam Integer size) {

        try {
            List<CategoryRespond> respones = new ArrayList<>();
            categoryService.getAll(name, page - 1, size).forEach(v -> {
                respones.add(v.toResponeDTO());
            });

            return ResponseEntity.ok(respones);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Result("Fail", e.getMessage(), null));
        }

    }


    @PostMapping("category")
    public ResponseEntity add(@RequestBody @Valid Category body) {
        try {
            //lưu lại
            return ResponseEntity.ok(categoryService.add(body));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Result("Fail", e.getMessage(), body));
        }
    }


    @PutMapping("category/{Id}")
    public ResponseEntity update(@PathVariable @Valid Integer Id, @RequestBody @Valid Category body) {
        try {

            return ResponseEntity.ok(categoryService.update(Id,body));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Result("Fail", e.getMessage(), Id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Result("Fail", e.getMessage(), body));
        }

    }


    @DeleteMapping("category/{Id}")
    public ResponseEntity delete(@PathVariable Integer Id) {
        try {

            return ResponseEntity.ok(categoryService.delete(Id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result<>("Fail", e.getMessage(), Id));
        }

    }


}
