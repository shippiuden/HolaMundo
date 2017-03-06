package com.zapata.edwin.holamundo;

/**
 * Created by edwinzapata on 27/02/17.
 */


public class DatosE{

    private int id;
    private String dedo;
    private Double PosX;
    private Double PosY;

    // Constructor de un objeto DatosE
    public DatosE(int id, String dedo, Double PosX, Double PosY) {
        this.id = id;
        this.dedo = dedo;
        this.PosX = PosX;
        this.PosY = PosY;
    }

    // Recuperar/establecer ID
    public int getID() {
        return id;
    }
    public void setID(int id) {
        this.id = id;
    }

    // Recuperar/establecer NOMBRE
    public String getDedo() {
        return dedo;
    }
    public void setDedo(String dedo) {
        this.dedo = dedo;
    }

    // Recuperar/establecer TELEFONO
    public Double getPosX() {
        return PosX;
    }
    public void setPosX(Double PosX) {
        this.PosX = PosX;
    }

    // Recuperar/establecer EMAIL
    public Double getPosY() {
        return PosY;
    }
    public void setPosY(Double PosY) {
        this.PosY = PosY;
    }


}
