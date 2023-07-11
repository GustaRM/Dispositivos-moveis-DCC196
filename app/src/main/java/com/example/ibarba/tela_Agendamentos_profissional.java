package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class tela_Agendamentos_profissional extends AppCompatActivity {

    private ArrayList<String> listaAgendamentosProfissional;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamentos_profissional);

        ListView lista = (ListView) findViewById(R.id.listaAgendamentos);
        ArrayList<String> agendamentos = preencherAgendamentos();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, agendamentos);
        lista.setAdapter(arrayAdapter);

        //Voltar para o menu anterior
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
                Toast.makeText(getApplicationContext(), "Agendamentos: "+agendamentos.get(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<String> preencherAgendamentos() {
        listaAgendamentosProfissional = MainActivity.bancoDeDados.getListaAgendamentosProfissionalByIDusuario(MainActivity.IDusuario);
        return listaAgendamentosProfissional;
    }
}