package br.com.recife.vacina.vacinarecife.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.recife.vacina.vacinarecife.R;

public class MainActivity extends AppCompatActivity {
    
    private EditText edtNascimento;
    private Button btnProximo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupComponents();
        
    }

    private void setupComponents() {
        edtNascimento = (EditText) findViewById(R.id.edtNascimento);
        btnProximo = (Button) findViewById(R.id.btnProximo);
        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtNascimento.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, getText(R.string.strValidacaoDtNascimento), Toast.LENGTH_SHORT).show();
                }
                Intent it = new Intent(view.getContext(), VacinasActivity.class);
                startActivity(it);
            }
        });
    }
}
