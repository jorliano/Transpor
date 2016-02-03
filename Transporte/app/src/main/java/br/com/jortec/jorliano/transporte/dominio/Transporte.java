package br.com.jortec.jorliano.transporte.dominio;

import java.lang.String;import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Jorliano on 03/02/2016.
 */
public class Transporte extends RealmObject {

    @PrimaryKey
    private long id;
    private String modelo;
    private String marca;
    private int ano;
    private double km;
    private double potencia;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public double getPotencia() {
        return potencia;
    }

    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }
}
