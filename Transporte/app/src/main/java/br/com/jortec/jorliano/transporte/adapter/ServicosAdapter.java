package br.com.jortec.jorliano.transporte.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.jortec.jorliano.transporte.R;
import br.com.jortec.jorliano.transporte.dominio.Servicos;
import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

/**
 * Created by Jorliano on 03/02/2016.
 */

public class ServicosAdapter extends RealmBasedRecyclerViewAdapter<Servicos,
        ServicosAdapter.ViewHolder> {

    public ServicosAdapter(Context ccontext, RealmResults<Servicos> realmResults,
                           boolean automaticUpdate, boolean animacaoTipo){
        super(ccontext, realmResults, automaticUpdate, animacaoTipo);
    }

    public class ViewHolder extends RealmViewHolder {

        public TextView descricao;
        public TextView periodo;
        public TextView proximaTroca;
        public ImageView image;


        public ViewHolder(LinearLayout container) {
            super(container);
            this.descricao = (TextView) container.findViewById(R.id.descricao);
            this.periodo = (TextView) container.findViewById(R.id.periodo);
            this.image = (ImageView) container.findViewById(R.id.image);
            this.proximaTroca = (TextView) container.findViewById(R.id.proximaTroca);
        }
    }

    @Override
    public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int viewType) {
        View v = inflater.inflate(R.layout.item_cardiview_servicos, viewGroup, false);
        ViewHolder vh = new ViewHolder((LinearLayout) v);
        return vh;
    }

    @Override
    public void onBindRealmViewHolder(ViewHolder viewHolder, int position) {
        Servicos servicos = realmResults.get(position);
        viewHolder.descricao.setText(servicos.getDescricao());
        viewHolder.periodo.setText("periodo "+servicos.getPeriodo()+" km");
        viewHolder.proximaTroca.setText("prox.troca "+servicos.getProximaTroca()+" km");
        viewHolder.image.setImageResource(R.drawable.sandeiro);
    }
}
