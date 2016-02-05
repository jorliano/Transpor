package br.com.jortec.jorliano.transporte.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import br.com.jortec.jorliano.transporte.fragmento.ConfiguracaoFragment;
import br.com.jortec.jorliano.transporte.fragmento.ServicosFragment;
import br.com.jortec.jorliano.transporte.fragmento.SobreFragment;
import br.com.jortec.jorliano.transporte.fragmento.TransporteFragment;

/**
 * Created by Jorliano on 03/02/2016.
 */

public class TabsAdapter extends FragmentPagerAdapter {
    private Context context;
    private String[] titulos = {"INICIO","SERVIÇOS","CONFIGURAÇÃO","SOBRE"};

    public TabsAdapter(FragmentManager fm, Context c) {
        super(fm);
        context = c;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        Log.i("POSICAO", "posicao "+position);

     //   position = position == 0 ? 0 : position - 1;

        Log.i("POSICAO", "posicao "+position);

        if(position == 0){ // INICIO
            frag = new TransporteFragment();
        }
        else if(position == 1){ // PEÇAS
            frag = new ServicosFragment();
        }
        else if(position == 2){ // CONFIGURAÇÃO
            frag = new ConfiguracaoFragment();
        }
        else if(position == 3){ // SOBRE
            frag = new SobreFragment();
        }

        return frag;
    }

    @Override
    public int getCount() {
        return titulos.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (titulos[ position]);
    }
}