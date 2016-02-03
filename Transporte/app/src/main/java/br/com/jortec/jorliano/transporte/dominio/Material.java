package br.com.jortec.jorliano.transporte.dominio;

import java.lang.String;import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Jorliano on 03/02/2016.
 */
public class Material extends RealmObject {

    @PrimaryKey
    private long id;
    private String nome;
    private String descricao;
    private double valor;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
