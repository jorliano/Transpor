package br.com.jortec.jorliano.transporte;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.google.android.gms.gcm.GcmListenerService;

import br.com.jortec.jorliano.transporte.dominio.PushMessage;
import br.com.jortec.jorliano.transporte.extras.Util;
import de.greenrobot.event.EventBus;

/**
 * Created by Jorliano on 27/01/2016.
 */
public class MyGcmListenerService extends GcmListenerService {
    @Override
    public void onMessageReceived(String from, Bundle data) {
        //super.onMessageReceived(from, data);

        Log.i("Dados recebidos ", data.toString());

        // String nome = data.getString("title");
        //PushMessage message = new PushMessage("message", nome);

        if (!Util.isMyApplicationTaskOnTop(this)) {

            setNotificationApp(data);
        }

        EventBus.getDefault().post(new PushMessage(data.getString("title"), data.getString("message")));

    }

    private void setNotificationApp(final Bundle data) {
        final int id = 3030;

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setTicker(data.getString("message"))
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle(data.getString("title"))
                .setContentText(data.getString("message"))
                .setAutoCancel(true);

        Intent it = new Intent(this, NotificacaoActivity.class);
        it.putExtra("dados", data);

        PendingIntent pi = PendingIntent.getActivity(this, 0, it, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pi);

        // BIG CONTENT
            NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
            //bigText.setBigContentTitle( data.getString("title") );
            bigText.bigText(data.getString("message"));
            builder.setStyle(bigText);

            /*NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
                String[] messages = new String[6];
                builder.setNumber(messages.length);
            inboxStyle.setSummaryText("thiengocalopsita@gmail.com");
            for( int i = 0; i < messages.length; i++ ){
                messages[i] = "Test "+(new Random()).nextInt(9999);
                inboxStyle.addLine( messages[i] );
            }
            builder.setStyle(inboxStyle);*/

        //builder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(uri);

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(id, builder.build());
    }
}
