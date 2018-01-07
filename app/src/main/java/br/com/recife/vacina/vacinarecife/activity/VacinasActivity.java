package br.com.recife.vacina.vacinarecife.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.util.List;

import br.com.recife.vacina.vacinarecife.R;
import br.com.recife.vacina.vacinarecife.adapter.VacinaAdapter;
import br.com.recife.vacina.vacinarecife.model.vacina.Data;
import br.com.recife.vacina.vacinarecife.model.vacina.Records;
import br.com.recife.vacina.vacinarecife.interfaces.AsyncResponse;
import br.com.recife.vacina.vacinarecife.mvp.vacinas.IVacinasInteractorImpl;
import br.com.recife.vacina.vacinarecife.mvp.vacinas.IVacinasPresenter;
import br.com.recife.vacina.vacinarecife.mvp.vacinas.IVacinasPresenterImpl;
import br.com.recife.vacina.vacinarecife.mvp.vacinas.IVacinasView;
import br.com.recife.vacina.vacinarecife.service.RecordService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VacinasActivity extends AppCompatActivity implements IVacinasView {

    private ListView lstVacinas;
    private ProgressBar progressBar;
    private VacinaAdapter adapter;
    private IVacinasPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacinas);
        setUpComponents();
    }

    @Override
    protected void onDestroy() {
        if (presenter != null)
            presenter.onDestroy();
        super.onDestroy();
    }

    private void setUpComponents() {
        lstVacinas = (ListView) findViewById(R.id.lstVacinas);
        progressBar = (ProgressBar) findViewById(R.id.pgrBar);
        presenter = new IVacinasPresenterImpl(this, new IVacinasInteractorImpl());
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
    public void loadVacinas(List<Records> records) {
        if (records != null) {
            adapter = new VacinaAdapter(records, getApplicationContext());
            lstVacinas.setAdapter(adapter);
        }
    }

    @Override
    public void showErro(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setTitle("Erro");
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create();
    }
}
