package br.com.recife.vacina.vacinarecife.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class PostosActivity extends Fragment implements IPostosView {

    private ListView lstPostos;
    private ProgressBar progressBar;
    private PostosAdapter adapter;
    private IPostosPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_postos, container, false);
        lstPostos = (ListView) view.findViewById(R.id.lstPostos);
        progressBar = (ProgressBar) view.findViewById(R.id.pgrBarPostos);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new IPostosPresenterImpl(this, new IPostosInteractorImpl());
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.loadPostos();
    }

    @Override
    public void onDestroy() {
        if (presenter != null)
            presenter.onDestroy();
        super.onDestroy();
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
            adapter = new PostosAdapter(records, getContext());
            lstPostos.setAdapter(adapter);
            lstPostos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Record record = (Record) adapterView.getItemAtPosition(position);
                    String geo = "geo:0,0?q=" + record.getEndereco();
                    Uri uri = Uri.parse(geo);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.google.android.apps.maps");
                    startActivity(intent);
                }
            });
        }
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
