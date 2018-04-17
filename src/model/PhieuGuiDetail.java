/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author vinh
 */
public class PhieuGuiDetail implements java.io.Serializable {

    private String idstk;
    private String idkhachHang;
    private String tenKhachHang;
    private String cmnd;
    private String tenLtk;
    private Boolean dongSo;
    private Date ngayMo;
    private float laiSuat;
    private String quocTich;

    /**
     * @return the idstk
     */
    public String getIdstk() {
        return idstk;
    }

    /**
     * @param idstk the idstk to set
     */
    public void setIdstk(String idstk) {
        this.idstk = idstk;
    }

    /**
     * @return the idkhachHang
     */
    public String getIdkhachHang() {
        return idkhachHang;
    }

    /**
     * @param idkhachHang the idkhachHang to set
     */
    public void setIdkhachHang(String idkhachHang) {
        this.idkhachHang = idkhachHang;
    }

    /**
     * @return the tenKhachHang
     */
    public String getTenKhachHang() {
        return tenKhachHang;
    }

    /**
     * @param tenKhachHang the tenKhachHang to set
     */
    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    /**
     * @return the cmnd
     */
    public String getCmnd() {
        return cmnd;
    }

    /**
     * @param cmnd the cmnd to set
     */
    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    /**
     * @return the tenLtk
     */
    public String getTenLtk() {
        return tenLtk;
    }

    /**
     * @param tenLtk the tenLtk to set
     */
    public void setTenLtk(String tenLtk) {
        this.tenLtk = tenLtk;
    }

    /**
     * @return the dongSo
     */
    public Boolean getDongSo() {
        return dongSo;
    }

    /**
     * @param dongSo the dongSo to set
     */
    public void setDongSo(Boolean dongSo) {
        this.dongSo = dongSo;
    }

    /**
     * @return the ngayMo
     */
    public Date getNgayMo() {
        return ngayMo;
    }

    /**
     * @param ngayMo the ngayMo to set
     */
    public void setNgayMo(Date ngayMo) {
        this.ngayMo = ngayMo;
    }

    /**
     * @return the laiSuat
     */
    public float getLaiSuat() {
        return laiSuat;
    }

    /**
     * @param laiSuat the laiSuat to set
     */
    public void setLaiSuat(float laiSuat) {
        this.laiSuat = laiSuat;
    }

    /**
     * @return the quocTich
     */
    public String getQuocTich() {
        return quocTich;
    }

    /**
     * @param quocTich the quocTich to set
     */
    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

}
