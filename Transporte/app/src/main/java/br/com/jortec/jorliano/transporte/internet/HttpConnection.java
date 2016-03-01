package br.com.jortec.jorliano.transporte.internet;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jorliano on 31/01/2016.
 */
public class HttpConnection {
    public static String getSetDataWeb(String url, String method, String data){

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        String answer = "";

        try{
            ArrayList<NameValuePair> valores = new ArrayList<NameValuePair>();
            //valores.add(new BasicNameValuePair("method", method));
            valores.add(new BasicNameValuePair(method, data));

            UrlEncodedFormEntity ur = new UrlEncodedFormEntity(valores);
            Log.i("URK ", String.valueOf(ur));

            httpPost.setEntity(new UrlEncodedFormEntity(valores));
            HttpResponse resposta = httpClient.execute(httpPost);
            answer = EntityUtils.toString(resposta.getEntity());
        }
        catch(NullPointerException e){ e.printStackTrace(); }
        catch(ClientProtocolException e){ e.printStackTrace(); }
        catch(IOException e){
          Log.i("Erro ", e.getMessage());
            e.printStackTrace();
        }

        return(answer);
    }
}