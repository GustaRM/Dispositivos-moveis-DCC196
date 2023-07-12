package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class tela_Menu_relatorio extends AppCompatActivity {
    private List<String> meses = new ArrayList<>();
    private List<String> anos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_relatorio);

        //Configura o spinner dos meses
        Spinner spinnerMes = findViewById(R.id.spinnerMes);
        // Cria uma lista com as opções do Spinner
        meses = MainActivity.bancoDeDados.getListaMesesAtendimento();

        // Cria um ArrayAdapter para converter a lista em um adaptador para o Spinner
        ArrayAdapter<String> adapterMes = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, meses);
        // Define o layout do dropdown
        adapterMes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Atribui o adaptador para o Spinner
        spinnerMes.setAdapter(adapterMes);


        //Configura o spinner dos anos
        Spinner spinnerAno = findViewById(R.id.spinnerAno);
        // Cria uma lista com as opções do Spinner
        anos = MainActivity.bancoDeDados.getListaAnosAtendimento();

        // Cria um ArrayAdapter para converter a lista em um adaptador para o Spinner
        ArrayAdapter<String> adapterAno = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, anos);
        // Define o layout do dropdown
        adapterAno.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Atribui o adaptador para o Spinner
        spinnerAno.setAdapter(adapterAno);


        //Voltar para o menu inicial (Gestor)
        Button btnConcluir = findViewById((R.id.btnConcluir));
        btnConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Relatório 1
        Button btnRelatorio1 = findViewById((R.id.btnRelatorio1));
        btnRelatorio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tela_Menu_relatorio.this, tela_Relatorio_1.class);
                intent.putExtra("mes", spinnerMes.getSelectedItem().toString());
                startActivity(intent);
            }
        });

        //Relatório 2
        Button btnRelatorio2 = findViewById((R.id.btnRelatorio2));
        btnRelatorio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tela_Menu_relatorio.this, tela_Relatorio_2.class);
                intent.putExtra("mes", spinnerMes.getSelectedItem().toString());
                startActivity(intent);
            }
        });

        //Relatório 3
        Button btnRelatorio3 = findViewById((R.id.btnRelatorio3));
        btnRelatorio3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tela_Menu_relatorio.this, tela_Relatorio_1.class);
                intent.putExtra("ano", spinnerAno.getSelectedItem().toString());
                startActivity(intent);
            }
        });

        //Relatório 4
        Button btnRelatorio4 = findViewById((R.id.btnRelatorio4));
        btnRelatorio4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tela_Menu_relatorio.this, tela_Relatorio_1.class);
                intent.putExtra("ano", spinnerAno.getSelectedItem().toString());
                startActivity(intent);
            }
        });
    }
}