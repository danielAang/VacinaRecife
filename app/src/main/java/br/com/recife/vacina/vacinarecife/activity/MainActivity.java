package br.com.recife.vacina.vacinarecife.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        edtNascimento = (EditText) findViewById(R.id.edtNascimento);
        edtNascimento.setInputType(InputType.TYPE_NULL);
        edtNascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDatePicker();
            }
        });
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
                        Intent it = new Intent(view.getContext(), SaudeActivity.class);
                        startActivity(it);
                    } catch (ParseException e) {
                        Toast.makeText(MainActivity.this, "Erro ao salvar a data.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void createDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Calendar _calendar = Calendar.getInstance();
                _calendar.set(Calendar.YEAR, year);
                _calendar.set(Calendar.MONTH, month);
                _calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                edtNascimento.setText(format.format(_calendar.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}
