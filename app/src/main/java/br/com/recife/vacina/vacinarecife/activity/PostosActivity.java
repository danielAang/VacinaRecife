package br.com.recife.vacina.vacinarecife.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import br.com.recife.vacina.vacinarecife.R;
import br.com.recife.vacina.vacinarecife.adapter.PostosAdapter;
import br.com.recife.vacina.vacinarecife.model.Record;
import br.com.recife.vacina.vacinarecife.mvp.postos.IPostosInteractorImpl;
import br.com.recife.vacina.vacinarecife.mvp.postos.IPostosPresenter;
import br.com.recife.vacina.vacinarecife.mvp.postos.IPostosPresenterImpl;
import br.com.recife.vacina.vacinarecife.mvp.postos.IPostosView;

public class PostosActivity extends AppCompatActivity implements IPostosView {

    private ListView lstPostos;
    private ProgressBar progressBar;
    private PostosAdapter adapter;
    private IPostosPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postos);
        setUpComponents();
    }

    @Override
    protected void onDestroy() {
        if (presenter != null)
            presenter.onDestroy();
        super.onDestroy();
    }

    private void setUpComponents() {
        lstPostos = (ListView) findViewById(R.id.lstPostos);
        progressBar = (ProgressBar) findViewById(R.id.pgrBarPostos);
        presenter = new IPostosPresenterImpl(this, new IPostosInteractorImpl());
        presenter.loadPostos();
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
            adapter = new PostosAdapter(records, getApplicationContext());
            lstPostos.setAdapter(adapter);
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
