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

/**
 * Created by Jorliano on 03/02/2016.
 */
public class TransporteFragment extends Fragment {
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


        tvMarca.setText("Renault");
        tvModelo.setText("Sandero");
        tvAno.setText("2012");
        tvKm.setText("30.000km");
        tvPotencia.setText("2.0 cc");
        ivToolbar.setImageDrawable(getResources().getDrawable(R.drawable.sandeiro));

        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_menu_edit));
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), CadastroTransporteActivity.class).putExtra("id",""));
            }
        });

        return view;
    }
}
