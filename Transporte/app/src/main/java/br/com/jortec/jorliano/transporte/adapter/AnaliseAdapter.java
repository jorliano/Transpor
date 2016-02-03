package br.com.jortec.jorliano.transporte.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.jortec.jorliano.transporte.R;
import br.com.jortec.jorliano.transporte.dominio.Analise;
import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

/**
 * Created by Jorliano on 03/02/2016.
 */

public class AnaliseAdapter extends RealmBasedRecyclerViewAdapter<Analise,
        AnaliseAdapter.ViewHolder> {

    public AnaliseAdapter (Context ccontext, RealmResults<Analise> realmResults,
                                        boolean automaticUpdate,boolean animacaoTipo  ){
        super(ccontext, realmResults, automaticUpdate, animacaoTipo);
    }

    public class ViewHolder extends RealmViewHolder {

        public TextView title;
        public TextView publishedDate;
        public ImageView image;
        public TextView storyAbstract;

        public ViewHolder(LinearLayout container) {
            super(container);
            this.title = (TextView) container.findViewById(R.id.title);
            this.publishedDate = (TextView) container.findViewById(R.id.date);
            this.image = (ImageView) container.findViewById(R.id.image);
            this.storyAbstract = (TextView) container.findViewById(R.id.story_abstract);
        }
    }

    @Override
    public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int viewType) {
        View v = inflater.inflate(R.layout.item_recycler_analise_view, viewGroup, false);
        ViewHolder vh = new ViewHolder((LinearLayout) v);
        return vh;
    }

    @Override
    public void onBindRealmViewHolder(ViewHolder viewHolder, int position) {
        Analise analise = realmResults.get(position);
        viewHolder.title.setText(analise.getTitulo());
        viewHolder.publishedDate.setText(analise.getData());
        viewHolder.image.setImageResource(android.R.drawable.ic_dialog_map);
        viewHolder.storyAbstract.setText(analise.getSobre());
    }
}
