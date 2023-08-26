package com.example.AplikasiJuzAmma;

public class ModelData {
    String nama = "", image_url = "", deskripsi = "";

    public ModelData(String nama, String image_url, String deskripsi) {
        this.nama = nama;
        this.image_url = image_url;
        this.deskripsi = deskripsi;
    }


    public String getNama() {
        return nama;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getDeskripsi() {
        return deskripsi;
    }
}

