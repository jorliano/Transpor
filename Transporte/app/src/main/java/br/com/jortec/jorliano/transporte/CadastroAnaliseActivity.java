package br.com.jortec.jorliano.transporte;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import br.com.jortec.jorliano.transporte.dominio.Analise;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class CadastroAnaliseActivity extends AppCompatActivity {
    private EditText edtTitulo;
    private EditText edtSubTitulo;
    private Button btSalvar;

    Realm realm;
    Analise analise;
    RealmResults<Analise> analises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_analise);

        realm = Realm.getInstance(this);
        analises = realm.where(Analise.class).findAll();

        edtTitulo = (EditText) findViewById(R.id.edtTitulo);
        edtSubTitulo = (EditText) findViewById(R.id.edtSubTitulo);
        btSalvar = (Button) findViewById(R.id.bt_salver);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                realm.beginTransaction();
                preencerDados();
                realm.copyToRealmOrUpdate(analise);
                realm.commitTransaction();

                Toast.makeText(v.getContext(),"Dados salvo ",Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }

    public void preencerDados(){

        analise = new Analise();
        //gerando id
        analises.sort("id", Sort.DESCENDING);
        long id = analises.size() == 0 ? 1 : analises.get(0).getId() +1 ;
        analise.setId(id);

        analise.setTitulo(edtTitulo.getText().toString());
        analise.setSobre(edtSubTitulo.getText().toString());
        analise.setData("10/10/2100");
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
