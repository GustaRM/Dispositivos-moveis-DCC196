package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tela_Menu_profissional extends AppCompatActivity {

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
                Intent intent = new Intent(tela_Menu_profissional.this, tela_Horarios_profissional.class);
                startActivity(intent);
            }
        });

        btnAtendimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirecionar para a tela de cadastro de profissionais.
                Intent intent = new Intent(tela_Menu_profissional.this, tela_Cadastro_profissional.class);
                startActivity(intent);
            }
        });

        Button btnSair = findViewById((R.id.btnSair));
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}