package br.com.jortec.jorliano.transporte;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import br.com.jortec.jorliano.transporte.dominio.Servicos;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class CadastroServicosActivity extends AppCompatActivity {
    private EditText edtDescricao;
    private EditText edtProximaTroca;
    private EditText edtPeriodo;
    private ImageView tbImagem;
    private Button btSalvar;
    private Button btTroca;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    Realm realm;
    Servicos servico;
    RealmResults<Servicos> servicos;
    int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_servicos);

        realm = Realm.getDefaultInstance();
        servicos = realm.where(Servicos.class).findAll();

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle( "Serviço");

        Toolbar toolbar = (Toolbar) findViewById(R.id.detalhe_toolbar);
        toolbar.setTitle("Serviço");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtDescricao = (EditText) findViewById(R.id.edt_descricao);
        edtProximaTroca = (EditText) findViewById(R.id.edt_proximaTroca);
        edtPeriodo = (EditText) findViewById(R.id.edt_periodo);
        tbImagem = (ImageView) findViewById(R.id.tb_imagem);
        btSalvar = (Button) findViewById(R.id.bt_salver);
        btTroca = (Button) findViewById(R.id.bt_troca);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                realm.beginTransaction();
                preencerDados();
                realm.copyToRealmOrUpdate(servico);
                realm.commitTransaction();

                Toast.makeText(v.getContext(),"Dados salvo ",Toast.LENGTH_SHORT).show();
                finish();

            }
        });

        btTroca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id > 0){

                    Intent intent = new Intent(v.getContext(),MateriaActivity.class);
                    intent.putExtra("id","");
                    startActivity(intent);

                }else{
                    Toast.makeText(v.getContext(),"Serviço não foi cadastrado ainda",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void preencerDados(){

        servico = new Servicos();
        //gerando id
        servicos.sort("id", Sort.DESCENDING);
        long id = servicos.size() == 0 ? 1 : servicos.get(0).getId() +1 ;
        servico.setId(id);

        servico.setDescricao(edtDescricao.getText().toString());
        servico.setProximaTroca(Float.parseFloat(edtProximaTroca.getText().toString()));
        servico.setPeriodo(Integer.parseInt(edtPeriodo.getText().toString()));
        servico.setData("10/10/2100");
        //analise.setSubTitulo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastro_analise, menu);
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
