package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu_gestor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_gestor);

        Button btnServicos = findViewById(R.id.btnHorarios);
        Button btnProfissionais = findViewById(R.id.btnAtendimentos);
        Button btnRelatorios = findViewById(R.id.btnRelatorios);

        btnServicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirecionar para a tela de cadastro de serviços.
                Intent intent = new Intent(Menu_gestor.this, Cadastro_servico.class);
                startActivity(intent);
            }
        });

        btnProfissionais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirecionar para a tela de cadastro de profissionais.
                Intent intent = new Intent(Menu_gestor.this, Cadastro_profissional.class);
                startActivity(intent);
            }
        });

        btnRelatorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirecionar para o menu de relatorios.
                Intent intent = new Intent(Menu_gestor.this, Menu_relatorio.class);
                startActivity(intent);
            }
        });

        //Voltar para o menu inicial (Cliente)
        Button btnSair = findViewById((R.id.btnSair));
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_gestor.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}