
// Generated 01-Apr-2018 13:25:34 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * KhachHang generated by hbm2java
 */
public class KhachHang  implements java.io.Serializable {


     private String maKH;
     private String CMND;
     private String diaChi;
     private Date ngaySinh;
     private String ngheNghiep;
     private String queQuan;
     private String quocTich;
     private String tenKH;

    public KhachHang() {
    }

	
    public KhachHang(String maKH) {
        this.maKH = maKH;
    }
    public KhachHang(String maKH, String CMND, String diaChi, Date ngaySinh, String ngheNghiep, String queQuan, String quocTich, String tenKH) {
       this.maKH = maKH;
       this.CMND = CMND;
       this.diaChi = diaChi;
       this.ngaySinh = ngaySinh;
       this.ngheNghiep = ngheNghiep;
       this.queQuan = queQuan;
       this.quocTich = quocTich;
       this.tenKH = tenKH;
    }
   
    public String getMaKH() {
        return this.maKH;
    }
    
    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }
    public String getCMND() {
        return this.CMND;
    }
    
    public void setCMND(String CMND) {
        this.CMND = CMND;
    }
    public String getDiaChi() {
        return this.diaChi;
    }
    
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    public Date getNgaySinh() {
        return this.ngaySinh;
    }
    
    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    public String getNgheNghiep() {
        return this.ngheNghiep;
    }
    
    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }
    public String getQueQuan() {
        return this.queQuan;
    }
    
    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }
    public String getQuocTich() {
        return this.quocTich;
    }
    
    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }
    public String getTenKH() {
        return this.tenKH;
    }
    
    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }




}


