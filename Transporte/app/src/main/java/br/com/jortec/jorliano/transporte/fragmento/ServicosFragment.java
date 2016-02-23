package br.com.jortec.jorliano.transporte.fragmento;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;

import br.com.jortec.jorliano.transporte.CadastroServicosActivity;
import br.com.jortec.jorliano.transporte.R;
import br.com.jortec.jorliano.transporte.adapter.ServicosAdapter;
import br.com.jortec.jorliano.transporte.dominio.Servicos;
import br.com.jortec.jorliano.transporte.dominio.Transporte;
import br.com.jortec.jorliano.transporte.interfaces.RecyclerViewOnclickListener;
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import de.greenrobot.event.EventBus;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Jorliano on 03/02/2016.
 */
public class ServicosFragment extends Fragment implements RecyclerViewOnclickListener {
    RealmRecyclerView realmRecyclerView;
    Realm realm;
    RealmResults<Servicos> servicos;
    Transporte transporte;
    FloatingActionButton fab;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.servicos_fragment, container, false);

        realmRecyclerView = (RealmRecyclerView) view.findViewById(R.id.realm_recycler_view);

        realm = Realm.getDefaultInstance();
        servicos = realm.where(Servicos.class).findAll();
        transporte = realm.where(Transporte.class).findFirst();

        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }


        ServicosAdapter adapter = new ServicosAdapter(view.getContext(), servicos, true, true, "cardview");
        adapter.setRecyclerViewOnclickListener(this);
        realmRecyclerView.setAdapter(adapter);


        fab = (FloatingActionButton) view.findViewById(R.id.fab_service);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fr = new ImagensServicoFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.cll, fr);
                fragmentTransaction.commit();


               // startActivity(new Intent(view.getContext(), CadastroServicosActivity.class));
            }
        });
        return view;
    }

    @Override
    public void onclickListener(View view, int position) {

      startActivity(new Intent(view.getContext(), CadastroServicosActivity.class)
             .putExtra(Servicos.ID, servicos.get(position).getId()));
    }

    //LISTENER
    public void onEvent(Servicos s) {
        servicos = realm.where(Servicos.class).findAll();
       // realmRecyclerView.notifyAll();
        ServicosAdapter adapter = new ServicosAdapter(getContext(), servicos, true, true, "cardview");
        adapter.setRecyclerViewOnclickListener(this);
       realmRecyclerView.setAdapter(adapter);

    };


    public void carregarServiços(int km){


        String descricao = "Oleo";
        int img = R.drawable.oleo;

        for (int i = 0; i < 3;i ++){
            realm.beginTransaction();
            Servicos servico = new Servicos();

            servico.setId(i);
            servico.setDescricao(descricao);
            servico.setUltimaTroca(km);
            servico.setPeriodo(1);
            servico.setProximaTroca(servico.getUltimaTroca() + (servico.getPeriodo() * 1000));
            servico.setData(new Date());
            servico.setImagem(img);


            realm.copyToRealmOrUpdate(servico);
            realm.commitTransaction();
            realm.close();

            descricao = descricao.equals("Oleo") ? "Manutenção" : "Pneu";
            img = img == R.drawable.oleo ? R.drawable.manutencao : R.drawable.roda;
        }
    }


}
