package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu_profissional extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_profissional);
        Button btnHorarios = findViewById(R.id.btnHorarios);
        Button btnAtendimentos = findViewById(R.id.btnAtendimentos);

        btnHorarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirecionar para a tela de horários do profissional.
                Intent intent = new Intent(Menu_profissional.this, Horarios_profissional.class);
                startActivity(intent);
            }
        });

        btnAtendimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirecionar para a tela de cadastro de profissionais.
                Intent intent = new Intent(Menu_profissional.this, Cadastro_profissional.class);
                startActivity(intent);
            }
        });
    }
}