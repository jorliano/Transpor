package br.com.jortec.jorliano.transporte.extras;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by Jorliano on 10/02/2016.
 */
public class  Formate {


    public  static String doubleParaMonetario(double valor){

        NumberFormat format = new DecimalFormat(",##0,00");

        String valorFormatado = format.format(valor);

        return valorFormatado;
    }

    public  static Double MonetarioParaDouble(String valor){

        String convert = valor.replace(",", "");
        convert.replace(".","");

        return Double.parseDouble(convert);
    }

    public  static String intParaKm(int valor){

       String valorFormatado = String.valueOf(valor);

       if(valor > 999){

           String quartaString = valorFormatado.substring(valorFormatado.length() - 4, valorFormatado.length() - 3);
           valorFormatado = valorFormatado.replace(quartaString , quartaString+".");
       }

        return valorFormatado;
    }

    public static String dataParaString(Date data){
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
        String dataFormatada = dateFormat.format(data);
        return  dataFormatada;
    }
    public static Date stringParaData(String data){
        Date dataFormatada = null;
        // DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        try {
            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
            dataFormatada = dateFormat.parse(data);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dataFormatada;
    }
}