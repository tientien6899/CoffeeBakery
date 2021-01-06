package com.example.coffeebakery;

public class Profile {


    public String hoten, sdt, sonhaduong, phuongxa, quanhuyen, tinhthanhpho;

    public Profile() {

    }

    public Profile(String hoten, String sdt, String sonhaduong, String phuongxa, String quanhuyen, String tinhthanhpho) {
        this.hoten = hoten;
        this.sdt = sdt;
        this.sonhaduong = sonhaduong;
        this.phuongxa = phuongxa;
        this.quanhuyen = quanhuyen;
        this.tinhthanhpho = tinhthanhpho;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getSonhaduong() {
        return sonhaduong;
    }

    public void setSonhaduong(String sonhaduong) {
        this.sonhaduong = sonhaduong;
    }

    public String getPhuongxa() {
        return phuongxa;
    }

    public void setPhuongxa(String phuongxa) {
        this.phuongxa = phuongxa;
    }

    public String getQuanhuyen() {
        return quanhuyen;
    }

    public void setQuanhuyen(String quanhuyen) {
        this.quanhuyen = quanhuyen;
    }

    public String getTinhthanhpho() {
        return tinhthanhpho;
    }

    public void setTinhthanhpho(String tinhthanhpho) {
        this.tinhthanhpho = tinhthanhpho;
    }
}
