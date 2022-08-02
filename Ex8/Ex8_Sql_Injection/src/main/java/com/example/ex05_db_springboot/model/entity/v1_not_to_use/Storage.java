package com.example.ex05_db_springboot.model.entity.v1_not_to_use;


import java.sql.Timestamp;
public class Storage {

    private Integer id;
    private String ten;
    private String diaDiem;
    private Timestamp ngayTao;
    private Timestamp ngaySua;
    public Integer getId() {
        return id;
    }
    public Storage()
    {
        this("","");
    }
    public Storage(String ten, String diaDiem) {
        this.ten = ten;
        this.diaDiem = diaDiem;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getTen() {
        return ten;
    }
    public void setTen(String ten) {
        this.ten = ten;
    }
    public String getDiaDiem() {
        return diaDiem;
    }
    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }
    public Timestamp getNgayTao() {
        return ngayTao;
    }
    public void setNgayTao(Timestamp ngayTao) {
        this.ngayTao = ngayTao;
    }
    public Timestamp getNgaySua() {
        return ngaySua;
    }
    public void setNgaySua(Timestamp ngaySua) {
        this.ngaySua = ngaySua;
    }

}