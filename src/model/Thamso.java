package model;
// Generated 11-Apr-2018 22:52:47 by Hibernate Tools 4.3.1



/**
 * Thamso generated by hbm2java
 */
public class Thamso  implements java.io.Serializable {


     private int idquyDinhSoTien;
     private String tenQuyDinh;
     private float soTienGuiToiThieu;
     private float soTienGuiToiDa;

    public Thamso() {
    }

	
    public Thamso(int idquyDinhSoTien, float soTienGuiToiThieu, float soTienGuiToiDa) {
        this.idquyDinhSoTien = idquyDinhSoTien;
        this.soTienGuiToiThieu = soTienGuiToiThieu;
        this.soTienGuiToiDa = soTienGuiToiDa;
    }
    public Thamso(int idquyDinhSoTien, String tenQuyDinh, float soTienGuiToiThieu, float soTienGuiToiDa) {
       this.idquyDinhSoTien = idquyDinhSoTien;
       this.tenQuyDinh = tenQuyDinh;
       this.soTienGuiToiThieu = soTienGuiToiThieu;
       this.soTienGuiToiDa = soTienGuiToiDa;
    }
   
    public int getIdquyDinhSoTien() {
        return this.idquyDinhSoTien;
    }
    
    public void setIdquyDinhSoTien(int idquyDinhSoTien) {
        this.idquyDinhSoTien = idquyDinhSoTien;
    }
    public String getTenQuyDinh() {
        return this.tenQuyDinh;
    }
    
    public void setTenQuyDinh(String tenQuyDinh) {
        this.tenQuyDinh = tenQuyDinh;
    }
    public float getSoTienGuiToiThieu() {
        return this.soTienGuiToiThieu;
    }
    
    public void setSoTienGuiToiThieu(float soTienGuiToiThieu) {
        this.soTienGuiToiThieu = soTienGuiToiThieu;
    }
    public float getSoTienGuiToiDa() {
        return this.soTienGuiToiDa;
    }
    
    public void setSoTienGuiToiDa(float soTienGuiToiDa) {
        this.soTienGuiToiDa = soTienGuiToiDa;
    }




}


