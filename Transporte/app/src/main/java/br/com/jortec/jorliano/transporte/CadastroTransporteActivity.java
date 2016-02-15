package br.com.jortec.jorliano.transporte;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import br.com.jortec.jorliano.transporte.dominio.Servicos;
import br.com.jortec.jorliano.transporte.dominio.Transporte;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class CadastroTransporteActivity extends AppCompatActivity {
    Realm realm;
    RealmResults<Transporte> transportes;

    private TextView modelo;
    private TextView marca;
    private TextView ano;
    private TextView km;
    private TextView potencia;
    private ImageView imagem;
    private RadioGroup rgTipo;
    private Button btSalvar;

    CollapsingToolbarLayout collapsingToolbarLayout;
    Transporte transporte;
    long id;
    int imagemSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_transporte);

        realm = Realm.getDefaultInstance();
        transportes = realm.where(Transporte.class).findAll();

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Serviço");

        Toolbar toolbar = (Toolbar) findViewById(R.id.detalhe_toolbar);
        toolbar.setTitle("Serviço");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        modelo = (EditText) findViewById(R.id.edt_modelo);
        marca = (EditText) findViewById(R.id.edt_marca);
        ano = (EditText) findViewById(R.id.edt_ano);
        km = (EditText) findViewById(R.id.edt_km);
        potencia = (EditText) findViewById(R.id.edt_potencia);
        rgTipo = (RadioGroup) findViewById(R.id.rgTipo);
        imagem = (ImageView) findViewById(R.id.tb_imagem);
        btSalvar = (Button) findViewById(R.id.bt_salvar_transporte);

        rgTipo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.rb_carro:  imagem.setImageResource(R.drawable.carro);
                        imagemSelecionada = R.drawable.carro;
                        break;
                    case R.id.rb_moto: imagem.setImageResource(R.drawable.moto);
                        imagemSelecionada = R.drawable.moto;
                        break;
                    case R.id.rb_caminhao: imagem.setImageResource(R.drawable.caminao);
                        imagemSelecionada = R.drawable.caminao;
                        break;
                    case R.id.rb_aviao: imagem.setImageResource(R.drawable.aviao);
                        imagemSelecionada = R.drawable.aviao;
                        break;
                }

            }
        });

        transporte = new Transporte();

        if (getIntent() != null && getIntent().getLongExtra(Transporte.ID, 0) > 0) {
            transporte.setId(getIntent().getLongExtra(Transporte.ID, 0));

            transporte = transportes.where().equalTo("id", transporte.getId()).findFirst();

            modelo.setText(transporte.getModelo());
            marca.setText(transporte.getMarca());
            ano.setText(String.valueOf(transporte.getAno()));
            km.setText(String.valueOf(transporte.getKm()));
            potencia.setText(String.valueOf(transporte.getPotencia()));
            rgTipo.check(transporte.getTipo());
            imagem.setImageResource(transporte.getImagem());

            btSalvar.setText("Atualizar");
        }


        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    realm.beginTransaction();
                    preencherDados(transporte);
                    realm.copyToRealmOrUpdate(transporte);
                    realm.commitTransaction();
                    //realm.close();

                    if(getIntent().getLongExtra(Transporte.ID, 0) == 0){

                        Transporte tranporte = realm.where(Transporte.class).findFirst();
                        String descricao = "Oleo";
                        int img = R.drawable.oleo;

                        for (int i = 0 ; i < 3; i++) {
                            realm.beginTransaction();
                            Servicos servico = new Servicos();

                            servico.setId(i + 1);
                            servico.setDescricao(descricao);
                            servico.setUltimaTroca(tranporte.getKm());
                            servico.setPeriodo(1);
                            servico.setProximaTroca(servico.getUltimaTroca() + (servico.getPeriodo() * 1000));
                            servico.setData(new Date());
                            servico.setImagem(img);


                            realm.copyToRealmOrUpdate(servico);
                            realm.commitTransaction();

                            if(i == 0){
                                descricao =  "Pneu";
                                img = R.drawable.roda;
                            }else
                                if(i == 1){
                                    descricao =  "Manutenção";
                                    img = R.drawable.manutencao;
                                }


                        }
                    }

                    Toast.makeText(v.getContext(), "Dados salvo", Toast.LENGTH_SHORT).show();

                    finish();
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), "erro " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public Transporte preencherDados(Transporte transporte) {

        if (transporte.getId() == 0) {
            //gerando id
            transportes.sort("id", Sort.DESCENDING);
            id = transportes.size() == 0 ? 1 : transportes.get(0).getId() + 1;
            transporte.setId(id);
        }

        transporte.setModelo(modelo.getText().toString());
        transporte.setMarca(marca.getText().toString());
        transporte.setAno(Integer.parseInt(ano.getText().toString()));
        transporte.setKm(Integer.parseInt(km.getText().toString()));
        transporte.setPotencia(Double.parseDouble(potencia.getText().toString()));
        transporte.setTipo(rgTipo.getCheckedRadioButtonId());
        transporte.setImagem(imagemSelecionada);

        return transporte;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        realm.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastro_transporte, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
