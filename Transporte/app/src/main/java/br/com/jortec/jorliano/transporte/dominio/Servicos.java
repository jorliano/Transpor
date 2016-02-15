package br.com.jortec.jorliano.transporte.dominio;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Jorliano on 02/02/2016.
 */
public class Servicos extends RealmObject {
    public static final String ID = "br.com.jortec.jorliano.transporte.dominio.servico.ID";
    public static final String PESQUISA = "br.com.jortec.jorliano.transporte.dominio.servico.PESQUISA";


    @PrimaryKey
    private long id;
    private String descricao;
    private Date data;
    private int proximaTroca;
    private int ultimaTroca;
    private int periodo;
    private int imagem;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getProximaTroca() {
        return proximaTroca;
    }

    public void setProximaTroca(int proximaTroca) {
        this.proximaTroca = proximaTroca;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public RealmList<Material> getMateriais() {
        return materiais;
    }

    public void setMateriais(RealmList<Material> materiais) {
        this.materiais = materiais;
    }

    public void setUltimaTroca(int ultimaTroca) {
        this.ultimaTroca = ultimaTroca;
    }

    public int getUltimaTroca() {
        return ultimaTroca;
    }
}
