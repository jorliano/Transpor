package br.com.jortec.jorliano.transporte;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import br.com.jortec.jorliano.transporte.dominio.Material;
import br.com.jortec.jorliano.transporte.dominio.Servicos;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class MaterialActivity extends AppCompatActivity {
    private TextView tvTitulo;
    private TextView tvData;
    private EditText edtValor;
    private Button btSalvar;
    private Button btAdd;

    Realm realm;
    RealmResults<Servicos> servicos;
    Material material;
    Servicos servico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia);


        tvTitulo = (TextView) findViewById(R.id.tv_title);
        tvData = (TextView) findViewById(R.id.edt_data);
        btSalvar = (Button) findViewById(R.id.bt_salvar);
        btAdd = (Button) findViewById(R.id.bt_add);

        realm = Realm.getDefaultInstance();
        servicos = realm.where(Servicos.class).findAll();

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout llParent = (LinearLayout) v.getParent();
                addMaterialsParaView(v, null);
            }
        });

        if (getIntent() != null && getIntent().getLongExtra(Servicos.ID, 0) > 0) {
            //Atualizar
            servico.setId(getIntent().getLongExtra(Servicos.ID, 0));

            servico = servicos.where().equalTo("id", servico.getId()).findAll().get(0);
            tvTitulo.setText(servico.getDescricao());
            tvData.setText(String.valueOf( servico.getData()));
            btSalvar.setText("Atualizar");

            for (Material m :servico.getMateriais()) {
                addMaterialsParaView(findViewById(R.id.bt_add), m);
            }

            removeMaterialDaView(findViewById(R.id.bt_remove));

        }

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String label = "Atualizado";

                try {

                    realm.beginTransaction();
                    servico.getMateriais().clear();
                    servico.getMateriais().addAll(getListaNotasView(v, servicos));
                    realm.commitTransaction();

                    Toast.makeText(v.getContext(), label + " com sucesso ", Toast.LENGTH_SHORT).show();
                    finish();

                } catch (Exception e) {
                    Log.i("id estudante erro ", "" + servico.getId());
                    Toast.makeText(v.getContext(), "falhou " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void addMaterialsParaView(View v, Material material) {
        LayoutInflater inflater = this.getLayoutInflater();
        LinearLayout llCuild = (LinearLayout) inflater.inflate(R.layout.box_material, null);

        EditText edtNome  = (EditText) llCuild.findViewById(R.id.edt_material);
        EditText edtValor = (EditText) llCuild.findViewById(R.id.edt_valor);
        Button btRemove = (Button) llCuild.findViewById(R.id.bt_remove);

        if (material != null) { //Atualizar
            edtNome.setText(String.valueOf(material.getDescricao()));
            edtValor.setText(String.valueOf(material.getValor()));
        }

        btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeMaterialDaView(v);
            }
        });

        float scale = getResources().getDisplayMetrics().density;
        int margin = (int) (5 * scale + 0.5f);

        LinearLayout.LayoutParams layoutParams = new ActionMenuView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(margin, margin, margin, margin);
        llCuild.setLayoutParams(layoutParams);

        LinearLayout llParent = (LinearLayout) v.getParent();
        llParent.addView(llCuild, llParent.getChildCount() - 1);
    }

    private void removeMaterialDaView(View v) {
        LinearLayout llParent = (LinearLayout) v.getParent().getParent();
        if (llParent.getChildCount() > 2) {
            llParent.removeView((LinearLayout) v.getParent());
        }
    }

    private List<Material> getListaNotasView(View view, RealmResults<Servicos> Servicos) {
        List<Material> lista = new LinkedList<>();
        RelativeLayout rlParent = (RelativeLayout) view.getParent();

        for (int i = 0; i < rlParent.getChildCount(); i++) {
            if (rlParent.getChildAt(i) instanceof ScrollView) {
                ScrollView scrollView = (ScrollView) rlParent.getChildAt(i);
                LinearLayout llChild = (LinearLayout) scrollView.getChildAt(0);

                for (int j = 0; j < llChild.getChildCount(); j++) {
                    if (llChild.getChildAt(j) instanceof LinearLayout) {

                        EditText edtMaterial = (EditText) llChild.getChildAt(j).findViewById(R.id.edt_material);
                        EditText edtValor = (EditText) llChild.getChildAt(j).findViewById(R.id.edt_valor);

                        Material m = new Material();
                        if (realm.where(Material.class).findAll().size() > 0) {
                            m.setId(realm.where(Material.class).findAll().where().findAllSorted("id", Sort.DESCENDING).get(0).getId() + 1 + j);
                        } else {
                            m.setId(1);
                        }

                        m.setDescricao(edtMaterial.getText().toString());
                        m.setValor(Double.parseDouble(edtValor.getText().toString()));

                        lista.add(m);
                    }
                }
            }
        }


        return lista;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_materia, menu);
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
