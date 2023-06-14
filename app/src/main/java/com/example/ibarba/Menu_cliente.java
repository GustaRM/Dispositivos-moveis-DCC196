package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu_cliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cliente);

        Button btnHorario = findViewById(R.id.btnHorario);
        Button btnCortes = findViewById(R.id.btnCortes);
        Button btnBarba = findViewById(R.id.btnBarba);
        Button btnOutros = findViewById(R.id.btnOutros);

        btnHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirecionar para a tela de cadastro de serviços.
                Intent intent = new Intent(Menu_cliente.this, Meus_Horarios.class);
                startActivity(intent);
            }
        });

        btnCortes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirecionar para a tela de cadastro de serviços.
                Intent intent = new Intent(Menu_cliente.this, Cortes.class);
                startActivity(intent);
            }
        });

        btnBarba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirecionar para a tela de cadastro de serviços.
                Intent intent = new Intent(Menu_cliente.this, Cortes.class);
                startActivity(intent);
            }
        });


        btnOutros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirecionar para a tela de cadastro de serviços.
                Intent intent = new Intent(Menu_cliente.this, Cortes.class);
                startActivity(intent);
            }
        });
    }
}