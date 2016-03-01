package br.com.jortec.jorliano.transporte;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import br.com.jortec.jorliano.transporte.adapter.BuscaAdapter;
import br.com.jortec.jorliano.transporte.dominio.Material;
import br.com.jortec.jorliano.transporte.dominio.Servicos;
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class PesquisaActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

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

        if (getIntent() != null && getIntent().getLongExtra(Servicos.ID, 0) > 0) {

            realm = Realm.getDefaultInstance();
            servico = realm.where(Servicos.class).equalTo("id", getIntent().getLongExtra(Servicos.ID, 0)).findFirst();

            materials = servico.getMateriais().where().findAll();
            materials.sort("data", Sort.DESCENDING);


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
        } else if (id == R.id.pesquisa_material) {
            schedukePesquisa();
        }

        return true;
    }

    //PESQUISA MANUTENÇOES
    private int ano, mes, dia;

    public void schedukePesquisa() {
        initData();
        Calendar calendarDefault = Calendar.getInstance();
        calendarDefault.set(ano, mes, dia);

        DatePickerDialog dataPickerDialog = DatePickerDialog.newInstance(
                this,
                calendarDefault.get(Calendar.YEAR),
                calendarDefault.get(Calendar.MONTH),
                calendarDefault.get(Calendar.DAY_OF_MONTH));

        Calendar cMin = Calendar.getInstance();
        Calendar cMax = Calendar.getInstance();

        materials.sort("data", Sort.ASCENDING);

        cMax.setTime(materials.get(materials.size() - 1).getData());
        cMin.setTime(materials.get(0).getData());
        dataPickerDialog.setMinDate(cMin);
        dataPickerDialog.setMaxDate(cMax);

        List<Calendar> dayList = new LinkedList<>();
        Calendar[] dayArray;

        Log.i("LOG", "Datas Inicial " + cMin.getTime() + "\nData final " + cMax.getTime());


        for (int i = 0; i < materials.size(); i++) {

            Calendar c = Calendar.getInstance();
            c.setTime(materials.get(i).getData());
            dayList.add(c);

        }
        dayArray = new Calendar[dayList.size()];
        for (int i = 0; i < dayArray.length; i++) {
            dayArray[i] = dayList.get(i);
        }

        dataPickerDialog.setSelectableDays(dayArray);
        dataPickerDialog.show(getFragmentManager(), "DataPickerDialog");
    }

    public void initData() {

        materials = servico.getMateriais().where().findAll();
        materials.sort("data", Sort.DESCENDING);

        if (ano == 0) {

            Calendar c = Calendar.getInstance();
            Log.i("LOG", "Data inicial " + materials.get(materials.size() - 1).getData());
            c.setTime(materials.get(materials.size() - 1).getData());

            ano = c.get(Calendar.YEAR);
            mes = c.get(Calendar.MONTH);
            dia = c.get(Calendar.DAY_OF_MONTH);
        }
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int i, int i1, int i2) {
        Calendar tDefault = Calendar.getInstance();
        tDefault.set(ano, mes, dia,0,0,0);
        ano = i;
        mes = i1;
        dia = i2;

        Log.i("LOG", "Data selecionada " + tDefault.getTime() + "\nData pesquisada " + materials.get(0).getData());


        materials = materials.where().equalTo("data", tDefault.getTime()).findAll();

        Log.i("LOG", "Data pesquisada "+materials.get(0).getData());
        //realmRecyclerView.notify();
        realmRecyclerView.setAdapter(new BuscaAdapter(this, materials, true, true));
    }

}
