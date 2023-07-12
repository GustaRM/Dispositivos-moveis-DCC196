package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Tela_Horarios_profissional extends AppCompatActivity {

    ArrayList<Spinner> listaStatus = new ArrayList<>();
    ArrayList<TextView> listaHorarios = new ArrayList<>();
    List<AgendaProfissional> listaAgenda;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios_profissional);
        linearLayout = findViewById(R.id.linearLayout);

        Button btnMarcaTodos = findViewById(R.id.btnMarcaTodos);
        Button btnDesmarcaTodos = findViewById(R.id.btnDesmarcaTodos);
        Button btnSalvar = findViewById(R.id.btnSalvar);
        DatePicker datePicker = findViewById(R.id.datePicker);

        //Pega a dada do datepicker  no formato "DD/MM/AAAA"
        String data = String.format("%02d/%02d/%04d", datePicker.getDayOfMonth(), datePicker.getMonth() + 1, datePicker.getYear());

        listaAgenda = MainActivity.bancoDeDados.buscarAgendaByDataProfissional(data, MainActivity.IDusuario);
        preencheHorarios(linearLayout, listaAgenda);

        btnMarcaTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < listaHorarios.size(); i++) {
                    TextView textViewHorario = listaHorarios.get(i);
                    Spinner spinnerStatus = listaStatus.get(i);

                    String horario = textViewHorario.getText().toString();

                    if ((horario.compareTo("08:00") >= 0 && horario.compareTo("12:00") <= 0) || (horario.compareTo("14:00") >= 0 && horario.compareTo("18:00") <= 0)) {
                        spinnerStatus.setSelection(0);
                    }
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

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < listaAgenda.size(); i++) {
                    AgendaProfissional agenda = listaAgenda.get(i);
                    Spinner spinner = listaStatus.get(i);
                    String statusSelecionado = spinner.getSelectedItem().toString();
                    agenda.setStatus(statusSelecionado);
                }
                //Pega a dada do datepicker  no formato "DD/MM/AAAA"
                String data = String.format("%02d/%02d/%04d", datePicker.getDayOfMonth(), datePicker.getMonth() + 1, datePicker.getYear());
                MainActivity.bancoDeDados.salvarAgendaByDataProfissional(data, MainActivity.IDusuario, listaAgenda);
            }
        });


        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Remove Spinners da listaStatus
                for (Spinner spinner : listaStatus) {
                    ((ViewGroup) spinner.getParent()).removeView(spinner);
                }
                listaStatus.clear();

                // Remove TextViews da listaHorarios
                for (TextView textView : listaHorarios) {
                    ((ViewGroup) textView.getParent()).removeView(textView);
                }
                listaHorarios.clear();

                //Limpa listaAgenda
                listaAgenda.clear();
                String data = String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, year);
                listaAgenda = MainActivity.bancoDeDados.buscarAgendaByDataProfissional(data, MainActivity.IDusuario);
                preencheHorarios(linearLayout, listaAgenda);

            }
        });
    }

    private void preencheHorarios(LinearLayout linearLayout, List<AgendaProfissional> listaAgenda) {

      //  LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
      //          LinearLayout.LayoutParams.MATCH_PARENT,
      //          LinearLayout.LayoutParams.WRAP_CONTENT
      //  );

        int numColunas = 2; // Define o número de colunas desejado
        int contador = 0;
        int posicaoStatus = 0;

        LinearLayout rowLayout = new LinearLayout(this);
        rowLayout.setOrientation(LinearLayout.HORIZONTAL);

        for (AgendaProfissional agenda : listaAgenda) {
            if (contador == numColunas) {
                // Adiciona a linha ao LinearLayout principal
                linearLayout.addView(rowLayout);

                // Cria uma nova linha
                rowLayout = new LinearLayout(this);
                rowLayout.setOrientation(LinearLayout.HORIZONTAL);

                contador = 0;
            }

            // Cria o TextView para exibir a hora
            TextView textViewHora = new TextView(this);
            textViewHora.setText(agenda.getHora());
            listaHorarios.add(textViewHora);

            // Cria o Spinner para exibir o status
            Spinner spinnerStatus = new Spinner(this);
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"Livre", "Indisponível", "Reservado cliente"});
            //spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            posicaoStatus = spinnerAdapter.getPosition(agenda.getStatus());
            spinnerStatus.setAdapter(spinnerAdapter);
            spinnerStatus.setSelection(posicaoStatus);
            listaStatus.add(spinnerStatus);

         //   // Cria um LinearLayout para representar uma coluna
          //  LinearLayout columnLayout = new LinearLayout(this);
          //  columnLayout.setOrientation(LinearLayout.VERTICAL);

            // Adiciona o TextView e o Spinner ao LinearLayout da coluna
            rowLayout.addView(textViewHora);
            rowLayout.addView(spinnerStatus);

            // Adiciona o LinearLayout da coluna ao LinearLayout da linha
          //  rowLayout.addView(columnLayout, layoutParams);

            contador++;
        }

        // Adiciona a última linha ao LinearLayout principal
        linearLayout.addView(rowLayout);

    }
}