package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class tela_Horarios_profissional extends AppCompatActivity {

    ArrayList<Spinner> listaStatus = new ArrayList<>();
    ArrayList<TextView> listaHorarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios_profissional);
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        Button btnMarcaTodos = findViewById((R.id.btnMarcaTodos));
        Button btnDesmarcaTodos = findViewById((R.id.btnDesmarcaTodos));

        int horaInicial = 6; // Hora inicial (06:00)
        int minutoInicial = 0; // Minuto inicial
        int intervaloMinutos = 30; // Intervalo de 30 minutos
        int colunas = 2; // Número de colunas
        int totalHorarios = 30; // Número total de CheckBoxes

        int contador = 0; // Contador para controlar a quantidade de CheckBoxes

        for (int i = 0; i < totalHorarios / colunas; i++) {
            LinearLayout rowLayout = new LinearLayout(this);
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.addView(rowLayout);

            for (int j = 0; j < colunas; j++) {

                // Cria um TextView para exibir o horário
                TextView textViewHorario = new TextView(this);
                textViewHorario.setText(String.format(Locale.getDefault(), "%02d:%02d", horaInicial, minutoInicial));
                listaHorarios.add(textViewHorario);

                // Cria um Spinner para selecionar o status
                Spinner spinnerStatus = new Spinner(this);
                listaStatus.add(spinnerStatus);


                ArrayAdapter<String> statusAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                        new String[]{"Livre", "Indisponível", "Reservado cliente"});
                spinnerStatus.setAdapter(statusAdapter);

                rowLayout.addView(textViewHorario);
                rowLayout.addView(spinnerStatus);

                contador++;

                minutoInicial += intervaloMinutos;
                if (minutoInicial >= 60) {
                    minutoInicial %= 60;
                    horaInicial++;
                }

                if (contador == totalHorarios) {
                    break;
                }
            }
        }

        btnMarcaTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Spinner spinner : listaStatus) {
                    spinner.setSelection(0);
                }
            }
        });

        btnDesmarcaTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Spinner spinner : listaStatus) {
                    spinner.setSelection(1);
                }
            }
        });



    }

    private void atualizaHorarios(String data) {


    }
}