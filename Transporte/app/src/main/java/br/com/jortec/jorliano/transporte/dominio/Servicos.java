package br.com.jortec.jorliano.transporte.dominio;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Jorliano on 02/02/2016.
 */
public class Servicos extends RealmObject {

    @PrimaryKey
    private long id;
    private String descricao;
    private String data;
    private Float proximaTroca;
    private int periodo;
    private long imagem;
    private RealmList<Material> materiais;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Float getProximaTroca() {
        return proximaTroca;
    }

    public void setProximaTroca(Float proximaTroca) {
        this.proximaTroca = proximaTroca;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public long getImagem() {
        return imagem;
    }

    public void setImagem(long imagem) {
        this.imagem = imagem;
    }

    public RealmList<Material> getMateriais() {
        return materiais;
    }

    public void setMateriais(RealmList<Material> materiais) {
        this.materiais = materiais;
    }
}
