package br.com.jortec.jorliano.transporte.fragmento;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.Serializable;

import br.com.jortec.jorliano.transporte.CadastroServicosActivity;
import br.com.jortec.jorliano.transporte.R;
import br.com.jortec.jorliano.transporte.dominio.Servicos;

/**
 * Created by Jorliano on 03/02/2016.
 */
public class ImagensServicoFragment extends Fragment implements OnClickListener {
    ImageButton ibMotor;
    ImageButton ibAnalise;
    ImageButton ibAmortecedor;
    ImageButton ibBateria;
    ImageButton ibTracao;
    ImageButton ibMarchas;
    ImageButton ibPneu;
    ImageButton ibManutencao;
    ImageButton ibOleo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.imagens_servico_fragment, container, false);

        ibMotor = (ImageButton) view.findViewById(R.id.ib_motor);
        ibAnalise = (ImageButton) view.findViewById(R.id.ib_analise);
        ibAmortecedor = (ImageButton) view.findViewById(R.id.ib_amortecedor);
        ibBateria = (ImageButton) view.findViewById(R.id.ib_bateria);
        ibTracao = (ImageButton) view.findViewById(R.id.ib_tracao);
        ibMarchas = (ImageButton) view.findViewById(R.id.ib_marcha);
        ibPneu = (ImageButton) view.findViewById(R.id.ib_pneu);
        ibManutencao = (ImageButton) view.findViewById(R.id.ib_manutencao);
        ibOleo = (ImageButton) view.findViewById(R.id.ib_oleo);


        ibMotor.setOnClickListener(this);
        ibAnalise.setOnClickListener(this);
        ibAmortecedor.setOnClickListener(this);
        ibBateria.setOnClickListener(this);
        ibTracao.setOnClickListener(this);
        ibMarchas.setOnClickListener(this);
        ibPneu.setOnClickListener(this);
        ibManutencao.setOnClickListener(this);
        ibOleo.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {

        Servicos servicos = new Servicos();

        switch (v.getId()){
            case R.id.ib_motor:
                servicos.setImagem(R.drawable.motor);
                servicos.setDescricao("Motor");
               break;
            case R.id.ib_analise:
                servicos.setImagem(R.drawable.analiese);
                servicos.setDescricao("Analise");
               break;
            case R.id.ib_amortecedor:
                servicos.setImagem(R.drawable.amortecedor);
                servicos.setDescricao("Amortecedor");
                break;
            case R.id.ib_bateria:
                servicos.setImagem(R.drawable.bateria);
                servicos.setDescricao("Bateria");
                break;
            case R.id.ib_tracao:
                servicos.setImagem(R.drawable.tracao);
                servicos.setDescricao("Tração");
                break;
            case R.id.ib_marcha:
                servicos.setImagem(R.drawable.marchas);
                servicos.setDescricao("Marcha");
                break;
            case R.id.ib_pneu:
                servicos.setImagem(R.drawable.roda);
                servicos.setDescricao("Pneu");
                break;
            case R.id.ib_manutencao:
                servicos.setImagem(R.drawable.manutencao);
                servicos.setDescricao("Manutenção");
                break;
            case R.id.ib_oleo:
                servicos.setImagem(R.drawable.oleo);
                servicos.setDescricao("Oleo");
                break;

        }

        startActivity(new Intent(v.getContext(), CadastroServicosActivity.class).putExtra("imagem", servicos));
        getFragmentManager().beginTransaction().remove(this).commit();
    }
}
