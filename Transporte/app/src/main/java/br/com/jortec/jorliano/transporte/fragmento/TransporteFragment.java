package br.com.jortec.jorliano.transporte.fragmento;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.jortec.jorliano.transporte.CadastroTransporteActivity;
import br.com.jortec.jorliano.transporte.R;
import br.com.jortec.jorliano.transporte.dominio.Transporte;
import br.com.jortec.jorliano.transporte.extras.Formate;
import de.greenrobot.event.EventBus;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Jorliano on 03/02/2016.
 */

public class TransporteFragment extends Fragment {
    Realm realm;
    RealmResults<Transporte> transportes;
    TextView tvMarca ;
    TextView tvModelo ;
    TextView tvAno ;
    TextView tvKm ;
    TextView tvPotencia ;
    ImageView ivToolbar;

    FloatingActionButton fab;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       final View view = inflater.inflate(R.layout.transporte_fragment, container,false);

         tvMarca = (TextView) view.findViewById(R.id.tv_marca);
         tvModelo = (TextView) view.findViewById(R.id.tv_modelo);
         tvAno = (TextView) view.findViewById(R.id.tv_ano);
         tvKm = (TextView) view.findViewById(R.id.tv_km);
         tvPotencia = (TextView) view.findViewById(R.id.tv_potencia);
         ivToolbar = (ImageView) view.findViewById(R.id.imagem);

        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }




        realm = Realm.getDefaultInstance();
        transportes = realm.where(Transporte.class).findAll();

        if(transportes.size() > 0){
            setaDados();


        }else {
            startActivity(new Intent(view.getContext(), CadastroTransporteActivity.class));
        }

        fab = (FloatingActionButton) view.findViewById(R.id.fab_transporte);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), CadastroTransporteActivity.class).putExtra(Transporte.ID, transportes.get(0).getId()));
            }
        });

        return view;
    }

    //LISTENER
    public void onEvent(Transporte t) {
        transportes = realm.where(Transporte.class).findAll();
        setaDados();
    };

    public void setaDados(){
        tvMarca.setText(transportes.get(0).getMarca());
        tvModelo.setText(transportes.get(0).getModelo());
        tvAno.setText(String.valueOf(transportes.get(0).getAno()));
        tvKm.setText(Formate.intParaKm(transportes.get(0).getKm()));
        tvPotencia.setText(String.valueOf(transportes.get(0).getPotencia()));
        ivToolbar.setImageResource(transportes.get(0).getImagem());
    }

}
