package br.com.jortec.jorliano.transporte.dominio;

import java.io.Serializable;

/**
 * Created by Jorliano on 29/01/2016.
 */
public class PushMessage implements Serializable {
    private String title;
    private String message;



    public PushMessage() {}
    public PushMessage(String title, String message) {
        this.setTitle(title);
        this.setMessage(message);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
