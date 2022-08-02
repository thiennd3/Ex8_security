package com.example.ex05_db_springboot.controller;

import com.example.ex05_db_springboot.controller.BaseController;
import com.example.ex05_db_springboot.model.entity.v2.Storage;
import com.example.ex05_db_springboot.services.StorageService;
import com.example.ex05_db_springboot.shared.Console;
import com.example.ex05_db_springboot.shared.Shared;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class StorageController implements BaseController<Storage> {

    @Autowired
    StorageService storageService;

    private String input = "x";


    @Override
    public void showAll() {
        List<Storage> list = storageService.getAll();

        for (Storage item : list) {
            show(item);
        }
    }

    @Override
    public void show(Storage item) {
        Console.show(item.getId() + Shared.phay() + item.getCode() + Shared.phay() + item.getName() + Shared.phay() + item.getAddress() + Shared.phay() +
                item.getCreateAt() + Shared.phay() + item.getUpdateAt());
    }

    @Override
    public void addNew() {
        try {
            Storage store = new Storage();
            store.setName(Console.getString("Nhập tên kho:"));
            store.setAddress(Console.getString("Nhập địa chỉ kho:"));
            store = storageService.add(store);
            Console.show("Thêm thành công.");

        } catch (NumberFormatException e) {
            Console.show("Phải nhập số ở trường này.");
            Console.show(e.getMessage());
        } catch (Exception ex) {
            Console.show("Thêm thất bại");
            Console.show(ex.getMessage());
        }
    }

    @Override
    public void update() {
        try {
            int id = Integer.parseInt(Console.getString("Nhập mã kho cần sửa:"));
            Storage store = storageService.getById(id);

            Console.show("Nhập lại thông tin Kho( trường nào giữ nguyên thì nhập x):");

            input = Console.getString("Tên kho:");

            if (!input.toLowerCase(Locale.ROOT).equals(Shared.EXIT))
                store.setName(input);
            input = Console.getString("Địa chỉ:");
            if (!input.toLowerCase(Locale.ROOT).equals(Shared.EXIT))
                store.setAddress(input);
            //Cập nhật vào Database
            storageService.update(store);
            Console.show("Sửa thành công.");
        } catch (NumberFormatException e) {
            Console.show("Phải nhập số ở trường này.");
            Console.show(e.getMessage());
        } catch (Exception ex) {
            Console.show("Sửa thất bại");
            Console.show(ex.getMessage());
        }
    }

    @Override
    public void delete() {
        try {
            int id = Integer.parseInt(Console.getString("Nhập mã kho cần xóa:"));
            storageService.deleteById(id);
            Console.show("Xóa thành công.");
        } catch (NumberFormatException e) {
            Console.show("Phải nhập số ở trường này.");
            Console.show(e.getMessage());
        } catch (Exception ex) {
            Console.show("Xóa thất bại");
            Console.show(ex.getMessage());
        }

    }


}
