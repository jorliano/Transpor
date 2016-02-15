package br.com.jortec.jorliano.transporte.fragmento;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.jortec.jorliano.transporte.R;
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.Realm;

/**
 * Created by Jorliano on 03/02/2016.
 */
public class SobreFragment extends Fragment {
    RealmRecyclerView realmRecyclerView;
    Realm realm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.sobre_fragment, container, false);

        TextView titulo = (TextView) view.findViewById(R.id.app);
        TextView texto = (TextView) view.findViewById(R.id.texto);

        titulo.setText("JORTEC");
        texto.setText("Esse aplicativo foi desenvolvido pela corporação JORTEC, seu objetivo é auxiliar os condutoes a " +
                      "ter um controle melhor de seus veiculos de transporte ");



        return view;
    }
}
