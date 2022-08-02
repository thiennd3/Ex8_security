package com.example.ex05_db_springboot.shared;

public class Shared {
    public static final String EXIT = "x";

    // đưa string vào giữa "" để thực thi sql
    public static   String sqlString(String msg)
    {
        return "\""+msg+"\"";
    }
    public static  String phay()
    {
        return ",";
    }



}
