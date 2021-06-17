package com.example.looting;

public class Barang {
    private int id;
    private String namabarang;
    private String deskbarang;
    private byte[] image;

    public Barang(String namabarang, String deskbarang, byte[] image, int id) {
        this.namabarang = namabarang;
        this.deskbarang = deskbarang;
        this.image = image;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamabarang() {
        return namabarang;
    }

    public void setNamabarang(String namabarang) {
        this.namabarang = namabarang;
    }

    public String getDeskbarang() {
        return deskbarang;
    }

    public void setDeskbarang(String deskBarang) {
        this.deskbarang = deskbarang;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}

