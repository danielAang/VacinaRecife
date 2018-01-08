package br.com.recife.vacina.vacinarecife.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class VacinasActivity extends AppCompatActivity implements IVacinasView {

    private ListView lstVacinas;
    private ProgressBar progressBar;
    private VacinaAdapter adapter;
    private IVacinasPresenter presenter;
    private Long dataNascimento;
    public static final String VACINA_RECIFE_PREFERENCES = "VacinaRecifePreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacinas);
        SharedPreferences settings = getApplicationContext().getSharedPreferences(VACINA_RECIFE_PREFERENCES, Context.MODE_PRIVATE);
        Long valorDefault = new Long("0");
        dataNascimento = settings.getLong("DATA_NASCIMENTO", valorDefault);
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
    public void loadVacinas(List<Record> records) {
        if (records != null) {
            adapter = new VacinaAdapter(records, getApplicationContext(), dataNascimento);
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
