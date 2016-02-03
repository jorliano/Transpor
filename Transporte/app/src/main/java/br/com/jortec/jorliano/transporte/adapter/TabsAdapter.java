package br.com.jortec.jorliano.transporte.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.com.jortec.jorliano.transporte.fragmento.AnaliseFragment;
import br.com.jortec.jorliano.transporte.fragmento.TransporteFragment;

/**
 * Created by Jorliano on 03/02/2016.
 */

public class TabsAdapter extends FragmentPagerAdapter {
    private Context context;
    private String[] titulos = {"INICIO","PEÇAS","CONFIGURAÇÃO","SOBRE"};

    public TabsAdapter(FragmentManager fm, Context c) {
        super(fm);
        context = c;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;

        if(position == 0){ // INICIO
            frag = new TransporteFragment();
        }
        else if(position == 1){ // PEÇAS
            frag = new AnaliseFragment();
        }
        else if(position == 2){ // CONFIGURAÇÃO
            frag = new TransporteFragment();
        }
        else if(position == 3){ // SOBRE
            frag = new TransporteFragment();
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