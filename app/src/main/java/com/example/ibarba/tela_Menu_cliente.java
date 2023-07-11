package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tela_Menu_cliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cliente);

        Button btnHorario = findViewById(R.id.btnHorario);
        Button btnAgendar = findViewById(R.id.btnAgendar);
        Button btnPrecos = findViewById(R.id.btnPrecos);

        btnHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirecionar para a tela de cadastro de serviços.
                Intent intent = new Intent(tela_Menu_cliente.this, tela_Agendamentos_cliente.class);
                startActivity(intent);
            }
        });

        btnAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirecionar para a tela de cadastro de serviços.
                Intent intent = new Intent(tela_Menu_cliente.this, tela_Agendar_cliente.class);
                startActivity(intent);
            }
        });

        btnPrecos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirecionar para a tela de cadastro de serviços.
                Intent intent = new Intent(tela_Menu_cliente.this, tela_Precos_cliente.class);
                startActivity(intent);
            }
        });

        //Voltar para a tela de Login
        Button btnSair = findViewById((R.id.btnSair));
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });

    }
}