package br.com.jortec.jorliano.transporte.fragmento;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.jortec.jorliano.transporte.CadastroServicosActivity;
import br.com.jortec.jorliano.transporte.R;
import br.com.jortec.jorliano.transporte.adapter.ServicoAdapter;
import br.com.jortec.jorliano.transporte.adapter.ServicosAdapter;
import br.com.jortec.jorliano.transporte.dominio.Servicos;
import br.com.jortec.jorliano.transporte.interfaces.RecyclerViewOnclickListener;
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Jorliano on 03/02/2016.
 */
public class ServicosFragment extends Fragment implements RecyclerViewOnclickListener {
    RealmRecyclerView realmRecyclerView;
    Realm realm;
    RealmResults<Servicos> servicos;
    FloatingActionButton fab;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.servicos_fragment, container, false);

        realmRecyclerView = (RealmRecyclerView) view.findViewById(R.id.realm_recycler_view);

        realm = Realm.getDefaultInstance();
        servicos = realm.where(Servicos.class).findAll();

        ServicosAdapter adapter = new ServicosAdapter(view.getContext(), servicos, true, true);
        adapter.setRecyclerViewOnclickListener(this);
        realmRecyclerView.setAdapter(adapter);


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

    @Override
    public void onclickListener(View view, int position) {

       startActivity(new Intent(view.getContext(),CadastroServicosActivity.class)
                         .putExtra(Servicos.ID, servicos.get(position).getId()));
    }
}
