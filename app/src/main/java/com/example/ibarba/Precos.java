package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Precos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precos);

        ListView lista = (ListView) findViewById(R.id.listaAgendamentos);
        ArrayList<String> precos = preencherPrecos();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, precos);
        lista.setAdapter(arrayAdapter);

        //Voltar para o menu inicial (Cliente)
        Button btnVoltar = findViewById((R.id.btnSair));
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Precos.this, Menu_cliente.class);
                startActivity(intent);
            }
        });
    }

    private ArrayList<String> preencherPrecos() {
        ArrayList<String> precos = new ArrayList<String>();
        precos.add("Barba - R$ 20");
        precos.add("Corte de cabelo - R$ 30");
        precos.add("Unha - R$ 30");
        precos.add("Massagem - R$ 50");
        precos.add("Sobrancelha - R$ 20");
        precos.add("Limpeza de pele - R$ 50");
        precos.add("Tintura de barba - R$ 20");
        precos.add("Hidratação capilar - R$ 30");

        return precos;
    }



}