package com.example.ex05_db_springboot.shared;

import java.util.Scanner;

public class Console {
    public static void show(String msg) {
        System.out.println(msg);
    }
    public static void show(String msg,int start,int end)
    {
        System.out.append(msg,start,end);
    }

    public static String getString(String msg) {
        show(msg);
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }

    public static int getInt(String msg) {
        show(msg);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }


   
}
