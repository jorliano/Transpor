package br.com.jortec.jorliano.transporte.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.jortec.jorliano.transporte.R;
import br.com.jortec.jorliano.transporte.dominio.Material;
import br.com.jortec.jorliano.transporte.dominio.Servicos;
import br.com.jortec.jorliano.transporte.extras.Formate;
import br.com.jortec.jorliano.transporte.interfaces.RecyclerViewOnclickListener;
import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

/**
 * Created by Jorliano on 09/02/2016.
 */

public class BuscaAdapter extends RealmBasedRecyclerViewAdapter<Material,
        BuscaAdapter.ViewHolder> {

    private RecyclerViewOnclickListener recyclerViewOnclickListener;

    List<Date> datasAnteriosres = new ArrayList<>();

    public BuscaAdapter(Context ccontext, RealmResults<Material> realmResults,
                        boolean automaticUpdate, boolean animacaoTipo) {
        super(ccontext, realmResults, automaticUpdate, animacaoTipo);
    }

    public class ViewHolder extends RealmViewHolder implements View.OnClickListener {

        public TextView nome;
        public TextView valor;
        public TextView data;


        public ViewHolder(LinearLayout container) {
            super(container);
            this.nome = (TextView) container.findViewById(R.id.tv_material_nome);
            this.valor = (TextView) container.findViewById(R.id.tv_material_valor);
            this.data = (TextView) container.findViewById(R.id.tv_material_data);

            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (recyclerViewOnclickListener != null) {
                recyclerViewOnclickListener.onclickListener(v, getPosition());
            }
        }
    }

    public void setRecyclerViewOnclickListener(RecyclerViewOnclickListener r) {
        recyclerViewOnclickListener = r;
    }

    @Override
    public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int viewType) {
        View v = inflater.inflate(R.layout.item_recyclerview_material, viewGroup, false);
        ViewHolder vh = new ViewHolder((LinearLayout) v);
        return vh;
    }


    @Override
    public void onBindRealmViewHolder(ViewHolder viewHolder, int position) {
        Material materiais = realmResults.get(position);

        viewHolder.nome.setText(materiais.getDescricao());
        viewHolder.valor.setText(Formate.doubleParaMonetario(materiais.getValor()));
        viewHolder.data.setText(Formate.dataParaString(materiais.getData()));


        if (!datasAnteriosres.isEmpty() && datasAnteriosres.contains( materiais.getData())){
            viewHolder.data.setVisibility(View.GONE);
        }else{
            datasAnteriosres.add(materiais.getData());
        }


    }
}
