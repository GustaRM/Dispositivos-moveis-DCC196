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

public class Meus_Horarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_horarios);

        ListView lista = (ListView) findViewById(R.id.listaAgendamentos);
        ArrayList<String> agendamentos = preencherAgendamentos();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, agendamentos);
        lista.setAdapter(arrayAdapter);

        //Voltar para o menu inicial (Cliente)
        Button btnVoltar = findViewById((R.id.btnSair));
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Meus_Horarios.this, Menu_cliente.class);
                startActivity(intent);
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Horários: "+agendamentos.get(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<String> preencherAgendamentos() {
        ArrayList<String> agendamentos = new ArrayList<String>();
        agendamentos.add("Massagem - 08:00");
        agendamentos.add("Sobrancelha - 09:00");

        return agendamentos;
    }
}