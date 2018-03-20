package com.example.yassine.test;



public class Etablissement {
    private int idEtab;
    private String label;
    private String name;
    private int img;
    private double lat;
    private double log;

    public Etablissement()
    {

    }

    public Etablissement (String label, String name, int img, double lat, double log){
        this.label= label;
        this.name= name;
        this.img=img;
        this.lat=lat;
        this.log=log;
    }

    public Etablissement (int idEtab,String label,String name, double lat, double log,int img)
    {
        this.idEtab=idEtab;
        this.label=label;
        this.name=name;
        this.lat=lat;
        this.log=log;
        this.img=img;
    }

    public int getIdEtab() {
        return idEtab;
    }

    public void setIdEtab(int idEtab) {
        this.idEtab = idEtab;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLog() {
        return log;
    }

    public void setLog(double log) {
        this.log = log;
    }
}
