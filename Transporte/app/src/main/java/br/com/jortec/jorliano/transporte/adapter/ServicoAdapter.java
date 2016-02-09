package br.com.jortec.jorliano.transporte.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.jortec.jorliano.transporte.R;
import br.com.jortec.jorliano.transporte.dominio.Servicos;
import br.com.jortec.jorliano.transporte.interfaces.RecyclerViewOnclickListener;

/**
 * Created by Jorliano on 09/02/2016.
 */
public class ServicoAdapter  extends RecyclerView.Adapter<ServicoAdapter.MyViewHolder> {

    private List<Servicos> listaServico;
    private LayoutInflater inflater;
    private RecyclerViewOnclickListener recyclerViewOnclickListener;
    private Context context;

    public ServicoAdapter(Context c, List l){
        listaServico = l;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context = c;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // View v = inflater.inflate(R.layout.item_moto, viewGroup, false);
        View v = inflater.inflate(R.layout.item_cardiview_servicos, viewGroup, false);
        MyViewHolder mvh= new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {


        viewHolder.descricao.setText(listaServico.get(position).getDescricao());
        viewHolder.periodo.setText("periodo " + listaServico.get(position).getPeriodo() + " km");
        viewHolder.proximaTroca.setText("prox.troca "+listaServico.get(position).getProximaTroca()+" km");
        viewHolder.image.setImageResource(R.drawable.sandeiro);


    }

    @Override
    public int getItemCount() {
        return listaServico.size();
    }

    public void  addListItem(Servicos m, int position){
        listaServico.add(m);
        notifyItemInserted(position);
    }

    public void setRecyclerViewOnclickListener (RecyclerViewOnclickListener r){
        recyclerViewOnclickListener = r;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView descricao;
        public TextView periodo;
        public TextView proximaTroca;
        public ImageView image;

        public MyViewHolder(View container) {
            super(container);

            this.descricao = (TextView) container.findViewById(R.id.descricao);
            this.periodo = (TextView) container.findViewById(R.id.periodo);
            this.image = (ImageView) container.findViewById(R.id.image);
            this.proximaTroca = (TextView) container.findViewById(R.id.proximaTroca);

           container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(recyclerViewOnclickListener != null){
                recyclerViewOnclickListener.onclickListener(v,getPosition());
            }
        }
    }
}