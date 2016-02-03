package br.com.jortec.jorliano.transporte.fragmento;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import br.com.jortec.jorliano.transporte.CadastroAnaliseActivity;
import br.com.jortec.jorliano.transporte.R;
import br.com.jortec.jorliano.transporte.adapter.AnaliseAdapter;
import br.com.jortec.jorliano.transporte.dominio.Analise;
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Jorliano on 03/02/2016.
 */
public class AnaliseFragment extends Fragment {
    RealmRecyclerView realmRecyclerView;
    Realm realm;
    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.analise_fragment, container, false);

        realmRecyclerView = (RealmRecyclerView) view.findViewById(R.id.realm_recycler_view);

        realm = Realm.getInstance(view.getContext());
        RealmResults<Analise> analises = realm.where(Analise.class).findAll();

        realmRecyclerView.setAdapter(new AnaliseAdapter(view.getContext(),analises,true,true));

        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_input_add));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), CadastroAnaliseActivity.class));
            }
        });
        return view;
    }
}
