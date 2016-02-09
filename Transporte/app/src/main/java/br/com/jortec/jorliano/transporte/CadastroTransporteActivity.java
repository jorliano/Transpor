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
        transporte.setKm(Double.parseDouble(km.getText().toString()));
        transporte.setPotencia(Double.parseDouble(potencia.getText().toString()));
        transporte.setTipo(rgTipo.getCheckedRadioButtonId());
        transporte.setImagem(R.drawable.sandeiro);

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
