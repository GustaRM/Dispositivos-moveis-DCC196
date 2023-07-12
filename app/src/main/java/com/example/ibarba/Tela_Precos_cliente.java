package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Tela_Precos_cliente extends AppCompatActivity {

    private ArrayList<String> listaPrecos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precos_cliente);

        ListView lista = (ListView) findViewById(R.id.listaPrecos);
        ArrayList<String> precos = preencherPrecos();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaPrecos);
        lista.setAdapter(arrayAdapter);

        //Voltar para o menu inicial (Cliente)
        Button btnVoltar = findViewById((R.id.btnSair));
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Consulte um desconto com o seu profissional preferido!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<String> preencherPrecos() {
        listaPrecos = MainActivity.bancoDeDados.getListaPrecosServicos();
        return listaPrecos;
    }
}