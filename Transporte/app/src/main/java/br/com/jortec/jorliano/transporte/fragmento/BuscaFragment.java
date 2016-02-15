package br.com.jortec.jorliano.transporte.fragmento;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.jortec.jorliano.transporte.CadastroServicosActivity;
import br.com.jortec.jorliano.transporte.PesquisaActivity;
import br.com.jortec.jorliano.transporte.R;
import br.com.jortec.jorliano.transporte.adapter.BuscaAdapter;
import br.com.jortec.jorliano.transporte.adapter.ServicosAdapter;
import br.com.jortec.jorliano.transporte.dominio.Servicos;
import br.com.jortec.jorliano.transporte.interfaces.RecyclerViewOnclickListener;
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Jorliano on 03/02/2016.
 */
public class BuscaFragment extends Fragment implements RecyclerViewOnclickListener {
    RealmRecyclerView realmRecyclerView;
    Realm realm;
    RealmResults<Servicos> servicos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.busca_fragment, container, false);

        realmRecyclerView = (RealmRecyclerView) view.findViewById(R.id.realm_recycler_service);

        realm = Realm.getDefaultInstance();
        servicos = realm.where(Servicos.class).findAll();

        ServicosAdapter adapter = new ServicosAdapter(view.getContext(), servicos, true, true, "recyclerview");
        adapter.setRecyclerViewOnclickListener(this);
        realmRecyclerView.setAdapter(adapter);


        return view;
    }

    @Override
    public void onclickListener(View view, int position) {
        startActivity(new Intent(view.getContext(), PesquisaActivity.class).putExtra(Servicos.ID, servicos.get(position).getId()));
    }
}
