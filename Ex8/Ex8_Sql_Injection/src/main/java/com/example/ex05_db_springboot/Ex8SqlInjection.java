package com.example.ex05_db_springboot;

import com.example.ex05_db_springboot.controller.BaseController;
import com.example.ex05_db_springboot.model.entity.v1_not_to_use.Category;
import com.example.ex05_db_springboot.model.entity.v1_not_to_use.Product;
import com.example.ex05_db_springboot.model.entity.v1_not_to_use.Storage;
import com.example.ex05_db_springboot.model.entity.v2.User;
import com.example.ex05_db_springboot.services.UserService;
import com.example.ex05_db_springboot.shared.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Ex8SqlInjection implements CommandLineRunner {
    @Qualifier("storageController")
    @Autowired
    BaseController storeCtrl;
    @Qualifier("productController")
    @Autowired
    BaseController productCtrl;

    @Autowired
    @Qualifier("categoriesController")
    BaseController categoriesCtrl;

    @Autowired
    UserService userService;
    int action = 4;
    String input = "x";
    String EXIT = "x";
    private List<Product> products;
    private List<Category> categories;
    private List<Storage> stores;
    int login=0;

    public static void main(String[] args) {
        SpringApplication.run(Ex8SqlInjection.class, args);
    }

    @Override
    public void run(String... args) throws Exception {



        Boolean isLogin=false;
        while (!isLogin)
        {
            switch (login)
            {
                case 0:
                    Console.show("1. Đăng nhập tồn tại SQL Injection \n" +
                            "2. Đăng nhập đã được bảo vệ");
                    login= Console.getInt("Chọn chức năng: ");
                    break;
                case 1:
                    String username= Console.getString("Tài khoản:");
                    String password= Console.getString("Mật khẩu:");
//                    password= new BCryptPasswordEncoder(5).encode(password);
                    User user =userService.getUser(username,password);
                    if (user==null) {

                        Console.show("Đăng nhập thất bại");
                    }
                    else {
                        Console.show("đăng nhập thành công");
                        isLogin=true;
                        login=0;
                    };

                    break;
                case 2:
                    String username1= Console.getString("Tài khoản:");
                    String password1= Console.getString("Mật khẩu:");
//                    password1= new BCryptPasswordEncoder(5).encode(password1);
                    User user1 =userService.getUserWithProtect(username1,password1);
                    if (user1==null) {

                        Console.show("Đăng nhập thất bại");
                    }
                    else {
                        Console.show("đăng nhập thành công");
                        isLogin=true;
                        login=0;
                    };

                    break;
                default:
                    login=0;
                    break;
            }


        }




        while (action != -1) {
            switch (action) {
                case 4:
                    show(
                            "-1: Thoát \n" +
                                    "1: Danh sách sản phẩm: \n" +
                                    "   10: Thêm sản phẩm\n" +
                                    "   11: Sửa sản phẩm\n" +
                                    "   12: Xóa sản phẩm\n" +
                                    "2: Danh sách loại danh mục\n" +
                                    "   20: Thêm danh muc\n" +
                                    "   21: Sửa danh muc\n" +
                                    "   22: Xóa danh muc\n" +
                                    "3: Danh Sách kho\n" +
                                    "   30: Thêm kho\n" +
                                    "   31: Sửa kho\n" +
                                    "   32: Xóa kho\n"

                    );
                    setAction(0);
                    break;
                case 0:
                    show("Nhập 4 để xem bảng chức năng:");

                    setAction(getInt("Chọn chức năng:"));
                    break;
                case 1:
                    productCtrl.showAll();
                    setAction(0);
                    break;
                // Thêm sản phẩm
                case 10:
                    productCtrl.addNew();
                    setAction(0);
                    break;

                case 11:
                    productCtrl.update();
                    setAction(0);
                    break;
                // Xóa sản phẩm
                case 12:
                    productCtrl.delete();

                    setAction(0);
                    break;

                case 2:
                    categoriesCtrl.showAll();
                    setAction(0);
                    break;
                //thêm loại danh mục
                case 20:
                    categoriesCtrl.addNew();
                    setAction(0);
                    break;

                // sửa danh mục
                case 21:
                    categoriesCtrl.update();
                    setAction(0);
                    break;
                //xóa danh mục
                case 22:
                    categoriesCtrl.delete();
                    setAction(0);
                    break;
                case 3:
                    storeCtrl.showAll();
                    setAction(0);
                    break;
                case 30:
                    storeCtrl.addNew();
                    setAction(0);
                    break;

                // sửa kho
                case 31:
                    storeCtrl.update();
                    setAction(0);
                    break;
                //xóa kho
                case 32:
                    storeCtrl.delete();
                    setAction(0);
                    break;
                default:
                    setAction(0);
                    break;
            }
        }
    }

    public void show(String msg) {
        System.out.println(msg);
    }

    public String getString(String msg) {
        show(msg);
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }

    public int getInt(String msg) {
        show(msg);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void setAction(int action) {
        this.action = action;
    }

}
