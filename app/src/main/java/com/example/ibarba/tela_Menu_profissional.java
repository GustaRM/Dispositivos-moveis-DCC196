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
        Button btnAgendamentos = findViewById(R.id.btnAgendamentos);

        btnHorarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirecionar para a tela de horários do profissional.
                Intent intent = new Intent(tela_Menu_profissional.this, tela_Horarios_profissional.class);
                startActivity(intent);
            }
        });

        btnAgendamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirecionar para a tela de agendamentos do profissionais.
                Intent intent = new Intent(tela_Menu_profissional.this, tela_Agendamentos_profissional.class);
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