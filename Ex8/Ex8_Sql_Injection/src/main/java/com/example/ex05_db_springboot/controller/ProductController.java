package com.example.ex05_db_springboot.controller;

import com.example.ex05_db_springboot.controller.BaseController;
import com.example.ex05_db_springboot.model.entity.v2.Product;
import com.example.ex05_db_springboot.services.ProductService;
import com.example.ex05_db_springboot.shared.Console;
import com.example.ex05_db_springboot.shared.Shared;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

@Component
public class ProductController implements BaseController<Product> {
    String input = "x";
    @Autowired
    ProductService productService;

    // hiển thị tất cả các sản phẩm
    @Override
    public void showAll() {
        List<Product> list = productService.getAll();
        for (Product item : list) {
            show(item);
        }
    }

    //hiển thị 1 sản phẩm
    @Override
    public void show(Product item) {
        Console.show(item.getId() + Shared.phay() + item.getCode() + Shared.phay() + item.getCode() + Shared.phay() + item.getName() + Shared.phay() + item.getCost() + Shared.phay() + item.getQuantity() + Shared.phay() + item.getSaleNumber() + Shared.phay() + item.getDescription());
    }

    @Override
    public void addNew() {
        try {

            Product product = new Product();
            Console.show("Nhập lại thông tin sản phẩm:");
            product.setName(Console.getString("Tên SP:"));
            product.setDescription(Console.getString("Mô tả:"));
            product.setCost(BigDecimal.valueOf(Long.valueOf(Console.getString("Đơn giá:"))));
            product.setStorageId(Integer.parseInt(Console.getString("Mã kho:")));
            product.setCategoryId(Integer.parseInt(Console.getString("Mã danh mục:")));
            product.setQuantity(Console.getInt("Số lượng:"));
            product = productService.add(product);
            Console.show("Thêm thành công. ");


        } catch (NumberFormatException ex) {
            Console.show("Phải nhập số ở trường này");
            Console.show("Thêm thất bại.");

        } catch (Exception e) {
            Console.show("Thêm thất bại.");
            Console.show(e.getMessage());

        }
    }

    @Override
    public void update() {
        try {

            int id = Integer.parseInt(Console.getString("Nhập ID sản phẩm cần cập nhật:"));
            Product product = productService.getById(id);
            Console.show("Nhập lại thông tin sản phẩm( trường nào giữ nguyên thì nhập x):");
            input = Console.getString("Tên SP:");
            if (!input.toLowerCase(Locale.ROOT).equals(Shared.EXIT)) {
                product.setName(input);
            }
            setDefault();
            input = Console.getString("Mô tả:");
            if (!input.toLowerCase(Locale.ROOT).equals(Shared.EXIT)) {
                product.setDescription(input);
            }

            input = Console.getString("Đơn giá:");
            if (!input.toLowerCase(Locale.ROOT).equals(Shared.EXIT)) {
                product.setCost(BigDecimal.valueOf(Long.valueOf(input)));
            }
            setDefault();
            input = Console.getString("Mã kho:");
            if (!input.toLowerCase(Locale.ROOT).equals(Shared.EXIT)) {
                product.setStorageId(Integer.parseInt(input));
            }
            setDefault();
            input = Console.getString("Mã danh mục:");
            if (!input.toLowerCase(Locale.ROOT).equals(Shared.EXIT)) {
                product.setCategoryId(Integer.parseInt(input));
            }
            productService.update(product);
            Console.show("Sửa thành công.");
        } catch (NumberFormatException ex) {
            Console.show("Phải nhập số ở trường này");
            Console.show(ex.getMessage());

        } catch (Exception e) {
            Console.show("sủa thất bại");
            Console.show(e.getMessage());
        }


    }


    @Override
    public void delete() {
        try {
            int id = Integer.parseInt(Console.getString("Nhập ID sản phẩm cần xóa:"));
            productService.deleteById(id);
            Console.show("Xóa thành công.");
        } catch (NumberFormatException e) {
            Console.show("Phải nhập số ở trường này.");
            Console.show(e.getMessage());
        } catch (Exception ex) {
            Console.show("Xóa thất bại");
            Console.show(ex.getMessage());
        }


    }

    public void setDefault() {
        this.input = "x";
    }
}
