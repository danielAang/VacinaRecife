package br.com.recife.vacina.vacinarecife.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import br.com.recife.vacina.vacinarecife.R;

/**
 * Created by morae on 11/01/2018.
 */

public class InternetConnection extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            if (isOnline(context)) {
                Toast.makeText(context, R.string.sem_conexao, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }
}
