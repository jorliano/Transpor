package br.com.jortec.jorliano.transporte;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.jortec.jorliano.transporte.dominio.PushMessage;
import de.greenrobot.event.EventBus;

public class NotificacaoActivity extends AppCompatActivity {
    private TextView tvTitulo;
    private TextView tvMensage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacao);

        tvTitulo = (TextView) findViewById(R.id.tv_msg_titulo);
        tvMensage = (TextView) findViewById(R.id.tv_msg_texto);

        EventBus.getDefault().register(this);

        Log.i("LOG","bundle com dados da mensage "+getIntent());
        if(getIntent() != null && getIntent().getBundleExtra("dados") != null){

            tvTitulo.setText(getIntent().getBundleExtra("dados").getString("title"));
            tvMensage.setText(getIntent().getBundleExtra("dados").getString("message"));

            Log.i("LOG", "bundle com dados existentes " +getIntent().getBundleExtra("dados").getString("titulo"));
        }

    }

    //LISTENER
    public void onEvent(final PushMessage pushMessage){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvTitulo.setText(pushMessage.getTitle());
                tvMensage.setText(pushMessage.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notificacao, menu);
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
