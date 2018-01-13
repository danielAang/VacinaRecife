package br.com.recife.vacina.vacinarecife.activity;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import br.com.recife.vacina.vacinarecife.R;
import br.com.recife.vacina.vacinarecife.adapter.VacinaAdapter;
import br.com.recife.vacina.vacinarecife.model.Record;
import br.com.recife.vacina.vacinarecife.mvp.vacinas.IVacinasInteractorImpl;
import br.com.recife.vacina.vacinarecife.mvp.vacinas.IVacinasPresenter;
import br.com.recife.vacina.vacinarecife.mvp.vacinas.IVacinasPresenterImpl;
import br.com.recife.vacina.vacinarecife.mvp.vacinas.IVacinasView;

public class VacinasActivity extends Fragment implements IVacinasView {

    private ListView lstVacinas;
    private ProgressBar progressBar;
    private VacinaAdapter adapter;
    private IVacinasPresenter presenter;
    private Long dataNascimento;
    public static final String VACINA_RECIFE_PREFERENCES = "VacinaRecifePreferences";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_vacinas, container, false);
        lstVacinas = (ListView) view.findViewById(R.id.lstVacinas);
        progressBar = (ProgressBar) view.findViewById(R.id.pgrBar);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new IVacinasPresenterImpl(this, new IVacinasInteractorImpl());
        SharedPreferences settings = getContext().getSharedPreferences(VACINA_RECIFE_PREFERENCES, Context.MODE_PRIVATE);
        dataNascimento = settings.getLong("DATA_NASCIMENTO", new Long("0"));
    }


    @Override
    public void onDestroy() {
        if (presenter != null)
            presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.loadVacinas();
    }

    @Override
    public void showProgressDialog() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void dismissProgressDialog() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void loadVacinas(List<Record> records) {
        if (records != null) {
            adapter = new VacinaAdapter(records, getContext(), dataNascimento);
            lstVacinas.setAdapter(adapter);
        }
    }

    @Override
    public void buildNotification() {
        NotificationManager mNotificationManager =
                (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        // The id of the channel.
        int id = 123456789;
        String channelId = "my_channel_01";
        CharSequence name = getString(R.string.channel_name);
        String description = getString(R.string.channel_description);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(channelId, name, importance);
            mChannel.setDescription(description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mNotificationManager.createNotificationChannel(mChannel);
        }
        Notification notification = new NotificationCompat.Builder(getContext(), channelId)
                .setContentTitle(name)
                .setContentText(description)
                .setBadgeIconType(R.mipmap.ic_launcher)
                .setNumber(5)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setAutoCancel(true)
                .build();
        mNotificationManager.notify(channelId, id, notification);
    }

    @Override
    public void showErro(int message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setTitle(R.string.erro_titulo);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create();
    }
}
