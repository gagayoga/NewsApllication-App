package com.example.newsaplication.ConnectAPI;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ModelNewsLokal {
    @SerializedName("idberita")
    private int idberita;
    @SerializedName("judulberita")
    private String judulberita;
    @SerializedName("deskripsiberita")
    private String deskripsiberita;
    @SerializedName("isiberita")
    private String isiberita;
    @SerializedName("penulis")
    private String penulis;

    public ModelNewsLokal(){

    }
    public ModelNewsLokal(int idberita,String judulberita,String deskripsiberita,String isiberita,String penulis){
       this.idberita=idberita;
       this.judulberita=judulberita;
       this.deskripsiberita=deskripsiberita;
       this.isiberita=isiberita;
       this.penulis=penulis;
    }

    public int getIdberita() {
        return idberita;
    }

    public void setIdberita(int idberita) {
        this.idberita = idberita;
    }

    public String getJudulberita() {
        return judulberita;
    }

    public void setJudulberita(String judulberita) {
        this.judulberita = judulberita;
    }

    public String getDeskripsiberita() {
        return deskripsiberita;
    }

    public void setDeskripsiberita(String deskripsiberita) {
        this.deskripsiberita = deskripsiberita;
    }

    public String getIsiberita() {
        return isiberita;
    }

    public void setIsiberita(String isiberita) {
        this.isiberita = isiberita;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }
}
