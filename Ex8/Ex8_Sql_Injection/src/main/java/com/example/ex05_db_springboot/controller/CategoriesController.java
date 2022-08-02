package com.example.ex05_db_springboot.controller;

import com.example.ex05_db_springboot.controller.BaseController;
import com.example.ex05_db_springboot.model.entity.v2.Category;
import com.example.ex05_db_springboot.services.CategoryService;
import com.example.ex05_db_springboot.shared.Console;
import com.example.ex05_db_springboot.shared.Shared;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class CategoriesController implements BaseController<Category> {

    private String input = "";

    @Autowired
    CategoryService categoryService;

    @Override
    public void showAll() {
        List<Category> list = categoryService.getAll();
        for (Category item : list) {
            show(item);
        }
    }

    @Override
    public void show(Category item) {
        Console.show(item.getId() + Shared.phay() + item.getCode() + Shared.phay() + item.getName() + Shared.phay() + item.getDescription() + Shared.phay() +
                item.getCreateAt() + Shared.phay() + item.getUpdateAt());

    }

    @Override
    public void addNew() {
        try {
            Category category = new Category();
            category.setName(Console.getString("Tên danh mục:"));
            category.setDescription(Console.getString("Mô tả:"));
            category = categoryService.add(category);
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
            int id = Console.getInt("Nhập id danh mục cần sửa:");
            Category category = categoryService.getById(id);
            Console.show("Nhập lại thông tin danh mục( trường nào giữ nguyên thì nhập x):");
            input = Console.getString("Tên danh mục:");
            if (!input.toLowerCase(Locale.ROOT).equals(Shared.EXIT))
                category.setName(input);
            input = Console.getString("Mô tả:");
            if (!input.toLowerCase(Locale.ROOT).equals(Shared.EXIT))
                category.setDescription(input);
            categoryService.update(category);
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
            int id = Integer.parseInt(Console.getString("Nhập ID danh mục cần xóa:"));
            categoryService.deleteById(id);
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
