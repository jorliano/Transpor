package br.com.jortec.jorliano.transporte;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import br.com.jortec.jorliano.transporte.adapter.BuscaAdapter;
import br.com.jortec.jorliano.transporte.dominio.Material;
import br.com.jortec.jorliano.transporte.dominio.Servicos;
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.Realm;
import io.realm.RealmResults;

public class PesquisaActivity extends AppCompatActivity {

    private Toolbar toolbar;

    RealmRecyclerView realmRecyclerView;
    Realm realm;
    RealmResults<Material> materials;
    Servicos servico;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

        realmRecyclerView = (RealmRecyclerView) findViewById(R.id.realm_recycler_pesquisa);

        if(getIntent() != null && getIntent().getLongExtra(Servicos.ID,0) > 0) {

            realm = Realm.getDefaultInstance();
            servico = realm.where(Servicos.class).equalTo("id", getIntent().getLongExtra(Servicos.ID,0)).findFirst();


            materials = realm.where(Material.class).findAll();
            for(int i = 0;i < servico.getMateriais().size(); i++) {
                Log.i("LOG ", "Itens ----> \n "+"Id " +servico.getMateriais().get(i).getId() +"\n"+ "Nome " + servico.getMateriais().get(i).getDescricao() + "\n" +
                        "Valor " + servico.getMateriais().get(i).getValor());



            }
            Log.i("LOG ", "SIZE " + materials.size());


            materials = servico.getMateriais().where().findAll();


        }

        realmRecyclerView.setAdapter(new BuscaAdapter(this, materials, true, true));

        toolbar = (Toolbar) findViewById(R.id.tb_pesquisa);
        toolbar.setTitle(servico.getDescricao());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pesquisa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
        }

        return true;
    }
}
