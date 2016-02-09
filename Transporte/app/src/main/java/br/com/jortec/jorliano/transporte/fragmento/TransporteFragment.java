package br.com.jortec.jorliano.transporte.fragmento;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.jortec.jorliano.transporte.CadastroTransporteActivity;
import br.com.jortec.jorliano.transporte.R;
import br.com.jortec.jorliano.transporte.dominio.Transporte;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Jorliano on 03/02/2016.
 */

public class TransporteFragment extends Fragment {
    Realm realm;
    RealmResults<Transporte> transportes;

    FloatingActionButton fab;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       final View view = inflater.inflate(R.layout.transporte_fragment, container,false);

        TextView tvMarca = (TextView) view.findViewById(R.id.tv_marca);
        TextView tvModelo = (TextView) view.findViewById(R.id.tv_modelo);
        TextView tvAno = (TextView) view.findViewById(R.id.tv_ano);
        TextView tvKm = (TextView) view.findViewById(R.id.tv_km);
        TextView tvPotencia = (TextView) view.findViewById(R.id.tv_potencia);
        ImageView ivToolbar = (ImageView) view.findViewById(R.id.imagem);

        realm = Realm.getDefaultInstance();
        transportes = realm.where(Transporte.class).findAll();

        if(transportes.size() > 0){
            tvMarca.setText(transportes.get(0).getMarca());
            tvModelo.setText(transportes.get(0).getModelo());
            tvAno.setText(String.valueOf(transportes.get(0).getAno()));
            tvKm.setText(String.valueOf(transportes.get(0).getKm()));
            tvPotencia.setText(String.valueOf(transportes.get(0).getPotencia()));
            ivToolbar.setImageResource(transportes.get(0).getImagem());

        }else {
            startActivity(new Intent(view.getContext(), CadastroTransporteActivity.class));
        }

        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_menu_edit));
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), CadastroTransporteActivity.class).putExtra(Transporte.ID,transportes.get(0).getId()));
            }
        });

        return view;
    }
}
