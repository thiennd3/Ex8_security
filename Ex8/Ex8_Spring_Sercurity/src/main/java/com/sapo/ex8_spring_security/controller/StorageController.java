package com.sapo.ex8_spring_security.controller;

import com.sapo.ex8_spring_security.sevice.StorageService;
import com.sapo.ex8_spring_security.shared.Result;
import com.sapo.ex8_spring_security.model.entity.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class StorageController implements BaseController<Storage, Object> {

    StorageService storageService;

    public StorageController(StorageService storageService) {
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


    @Override
    @GetMapping("storages")
    public List<Storage> getAll(@RequestParam String name, @RequestParam @Valid Integer page, @RequestParam Integer size) {
        return storageService.getAll(name, page - 1, size);
    }


    @Override
    @PostMapping("storage")
    public Object add(@RequestBody @Valid Storage body) {
        try {
            return storageService.add(body);
        } catch (Exception e) {

            return new Result("Fail", e.getMessage(), body);
        }
    }

    @Override
    @PutMapping("storage/{Id}")
    public Object update(@PathVariable Integer Id, @Valid @RequestBody Storage body) {
        try {
            return (Storage) storageService.update(Id,body);
        } catch (Exception e) {
            return new Result<>("Fail", e.getMessage(), body);
        }

    }

    @Override
    @DeleteMapping("storage/{Id}")
    public Object delete(@PathVariable Integer Id) {
        try {
            storageService.delete(Id);
            return Id;
        } catch (Exception e) {
            return new Result<>("Fail", e.getMessage(), null);
        }

    }


}
