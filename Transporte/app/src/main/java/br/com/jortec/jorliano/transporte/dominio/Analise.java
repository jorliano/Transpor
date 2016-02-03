package br.com.jortec.jorliano.transporte.dominio;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Jorliano on 02/02/2016.
 */
public class Analise extends RealmObject {

    @PrimaryKey
    private long id;
    private String titulo;
    private String data;
    private String sobre;
    private long imagem;
    private RealmList<Material> materiais;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public long getImagem() {
        return imagem;
    }

    public void setImagem(long imagem) {
        this.imagem = imagem;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSobre() {
        return sobre;
    }

    public void setSobre(String sobre) {
        this.sobre = sobre;
    }


    public RealmList<Material> getMateriais() {
        return materiais;
    }

    public void setMateriais(RealmList<Material> materiais) {
        this.materiais = materiais;
    }
}
