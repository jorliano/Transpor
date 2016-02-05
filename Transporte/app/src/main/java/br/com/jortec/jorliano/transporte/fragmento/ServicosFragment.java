package br.com.jortec.jorliano.transporte.fragmento;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.jortec.jorliano.transporte.CadastroServicosActivity;
import br.com.jortec.jorliano.transporte.R;
import br.com.jortec.jorliano.transporte.adapter.ServicosAdapter;
import br.com.jortec.jorliano.transporte.dominio.Servicos;
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Jorliano on 03/02/2016.
 */
public class ServicosFragment extends Fragment {
    RealmRecyclerView realmRecyclerView;
    Realm realm;
    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.servicos_fragment, container, false);

        realmRecyclerView = (RealmRecyclerView) view.findViewById(R.id.realm_recycler_view);

        realm = Realm.getDefaultInstance();
        RealmResults<Servicos> analises = realm.where(Servicos.class).findAll();

        realmRecyclerView.setAdapter(new ServicosAdapter(view.getContext(),analises,true,true));

        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_input_add));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), CadastroServicosActivity.class));
            }
        });
        return view;
    }
}
