package br.com.recife.vacina.vacinarecife.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.recife.vacina.vacinarecife.R;

public class MainActivity extends AppCompatActivity {

    public static final String VACINA_RECIFE_PREFERENCES = "VacinaRecifePreferences";
    private EditText edtNascimento;
    private Button btnProximo, btnPostos;
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupComponents();
        settings = getApplicationContext().getSharedPreferences(VACINA_RECIFE_PREFERENCES, Context.MODE_PRIVATE);
    }

    private void setupComponents() {
        edtNascimento = (EditText) findViewById(R.id.edtNascimento);
        btnProximo = (Button) findViewById(R.id.btnProximo);
        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtNascimento.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, getText(R.string.strValidacaoDtNascimento), Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        String dataNascimento = edtNascimento.getText().toString();
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        Date dtNascimento = format.parse(dataNascimento);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putLong("DATA_NASCIMENTO", dtNascimento.getTime()).apply();
                        Intent it = new Intent(view.getContext(), VacinasActivity.class);
                        startActivity(it);
                    } catch (ParseException e) {
                        Toast.makeText(MainActivity.this, "Erro ao salvar a data.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnPostos = (Button) findViewById(R.id.btnPostos);
        btnPostos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(view.getContext(), PostosActivity.class);
                startActivity(it);
            }
        });
    }
}
