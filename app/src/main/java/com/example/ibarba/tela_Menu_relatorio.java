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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_relatorio);

        //Configura o spinner dos meses
        Spinner spinnerMes = findViewById(R.id.spinnerMes);
        // Cria uma lista com as opções do Spinner
        List<String> meses = new ArrayList<>();
        meses.add("01/2023");
        meses.add("02/2023");
        meses.add("03/2023");
        meses.add("04/2023");
        meses.add("05/2023");

        // Cria um ArrayAdapter para converter a lista em um adaptador para o Spinner
        ArrayAdapter<String> adapterMes = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, meses);

        // Define o layout do dropdown
        adapterMes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Atribui o adaptador para o Spinner
        spinnerMes.setAdapter(adapterMes);


        //Configura o spinner dos tipos de relatório
        Spinner spinnerTipoRelatorio = findViewById(R.id.spinnerTipoRelatorio);
        // Cria uma lista com as opções do Spinner
        List<String> tiposRelatorios = new ArrayList<>();
        tiposRelatorios.add("Por Serviço");
        tiposRelatorios.add("Por Profissional");
        tiposRelatorios.add("Por Cliente");

        // Cria um ArrayAdapter para converter a lista em um adaptador para o Spinner
        ArrayAdapter<String> adapterTipoRelatorio = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tiposRelatorios);

        // Define o layout do dropdown
        adapterTipoRelatorio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Atribui o adaptador para o Spinner
        spinnerTipoRelatorio.setAdapter(adapterTipoRelatorio);

        //Voltar para o menu inicial (Gestor)
        Button btnVoltar = findViewById((R.id.btnSair));
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}