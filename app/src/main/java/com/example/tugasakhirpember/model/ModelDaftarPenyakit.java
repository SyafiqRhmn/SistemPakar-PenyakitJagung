package com.example.tugasakhirpember.model;

import java.io.Serializable;


public class ModelDaftarPenyakit implements Serializable {

    String strKode;
    String strDaftarPenyakit;

    public String getStrKode() {
        return strKode;
    }

    public void setStrKode(String strKode) {
        this.strKode = strKode;
    }

    public String getStrDaftarPenyakit() {
        return strDaftarPenyakit;
    }

    public void setStrDaftarPenyakit(String strDaftarPenyakit) {
        this.strDaftarPenyakit = strDaftarPenyakit;
    }

}
