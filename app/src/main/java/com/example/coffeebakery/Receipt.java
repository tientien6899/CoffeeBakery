package com.example.coffeebakery;

public class Receipt {
    String madon, ngaydat, magh, trangthai, tongtien;

    public Receipt() {
    }

    public Receipt(String madon, String ngaydat, String magh, String trangthai, String tongtien) {
        this.madon = madon;
        this.ngaydat = ngaydat;
        this.magh = magh;
        this.trangthai = trangthai;
        this.tongtien = tongtien;
    }

    public String getMadon() {
        return madon;
    }

    public void setMadon(String madon) {
        this.madon = madon;
    }

    public String getNgaydat() {
        return ngaydat;
    }

    public void setNgaydat(String ngaydat) {
        this.ngaydat = ngaydat;
    }

    public String getMagh() {
        return magh;
    }

    public void setMagh(String magh) {
        this.magh = magh;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }
}
