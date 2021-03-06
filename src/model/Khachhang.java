package model;
// Generated 11-Apr-2018 22:52:47 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Khachhang generated by hbm2java
 */
public class Khachhang  implements java.io.Serializable {


     private String idkhachHang;
     private String tenKhachHang;
     private String diaChi;
     private String cmnd;
     private Date ngaySinh;
     private String quocTich;
     private String queQuan;
     private String ngheNghiep;
     private Boolean isActive;

    public Khachhang() {
    }

	
    public Khachhang(String idkhachHang, String tenKhachHang, String diaChi, String cmnd, Date ngaySinh) {
        this.idkhachHang = idkhachHang;
        this.tenKhachHang = tenKhachHang;
        this.diaChi = diaChi;
        this.cmnd = cmnd;
        this.ngaySinh = ngaySinh;
    }
    public Khachhang(String idkhachHang, String tenKhachHang, String diaChi, String cmnd, Date ngaySinh, String quocTich, String queQuan, String ngheNghiep, Boolean isActive) {
       this.idkhachHang = idkhachHang;
       this.tenKhachHang = tenKhachHang;
       this.diaChi = diaChi;
       this.cmnd = cmnd;
       this.ngaySinh = ngaySinh;
       this.quocTich = quocTich;
       this.queQuan = queQuan;
       this.ngheNghiep = ngheNghiep;
       this.isActive = isActive;
    }
   
    public String getIdkhachHang() {
        return this.idkhachHang;
    }
    
    public void setIdkhachHang(String idkhachHang) {
        this.idkhachHang = idkhachHang;
    }
    public String getTenKhachHang() {
        return this.tenKhachHang;
    }
    
    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }
    public String getDiaChi() {
        return this.diaChi;
    }
    
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    public String getCmnd() {
        return this.cmnd;
    }
    
    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }
    public Date getNgaySinh() {
        return this.ngaySinh;
    }
    
    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    public String getQuocTich() {
        return this.quocTich;
    }
    
    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }
    public String getQueQuan() {
        return this.queQuan;
    }
    
    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }
    public String getNgheNghiep() {
        return this.ngheNghiep;
    }
    
    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }
    public Boolean getIsActive() {
        return this.isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }




}


