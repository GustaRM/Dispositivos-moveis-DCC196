package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class tela_AgendarServico extends AppCompatActivity {

    private Spinner spinnerServico;
    private Spinner spinnerProfissional;
    private Spinner spinnerData;
    private Spinner spinnerHorario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar_servico);

        spinnerServico = findViewById(R.id.spinnerServico);
        spinnerProfissional = findViewById(R.id.spinnerProfissional);
        spinnerData = findViewById(R.id.spinnerData);
        spinnerHorario = findViewById(R.id.spinnerHorario);

        configuraServicos();
        configuraProfissionais();

        ArrayAdapter<String> usuarioAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, obterListaProfissionais());
        spinnerProfissional.setAdapter(usuarioAdapter);

        spinnerServico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

         //       Toast.makeText(tela_AgendarServico.this,parent.getSelectedItem().toString(),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(tela_AgendarServico.this,"Nenhum selecionado",Toast.LENGTH_LONG).show();
            }

        });

        spinnerProfissional.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            //    Toast.makeText(tela_AgendarServico.this,parent.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
                configuraDatas();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(tela_AgendarServico.this,"Nenhum selecionado",Toast.LENGTH_LONG).show();
            }

        });

        spinnerData.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

         //       Toast.makeText(tela_AgendarServico.this,parent.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
                configuraHorarios();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(tela_AgendarServico.this,"Nenhum selecionado",Toast.LENGTH_LONG).show();
            }

        });
        spinnerHorario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

       //         Toast.makeText(tela_AgendarServico.this,parent.getSelectedItem().toString(),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(tela_AgendarServico.this,"Nenhum selecionado",Toast.LENGTH_LONG).show();
            }

        });

    }

    private void configuraServicos() {
        ArrayAdapter<String> servicoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, obterListaServicos());
        spinnerServico.setAdapter(servicoAdapter);
    }

    private void configuraProfissionais() {
        ArrayAdapter<String> usuarioAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, obterListaProfissionais());
        spinnerProfissional.setAdapter(usuarioAdapter);
    }

    private void configuraDatas() {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, obterListaDatas());
        spinnerData.setAdapter(dataAdapter);
    }

    private void configuraHorarios() {
        ArrayAdapter<String> horarioAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, obterListaHorarios());
        spinnerHorario.setAdapter(horarioAdapter);
    }

    private List<String> obterListaServicos() {
        List<String> listaServicos = new ArrayList<>();
        listaServicos.add("Serviço 1");
        listaServicos.add("Serviço 2");
        listaServicos.add("Serviço 3");
        return listaServicos;
    }

    private List<String> obterListaProfissionais() {
        List<String> listaProfissionais = new ArrayList<>();
        listaProfissionais.add("Fulano");
        listaProfissionais.add("Beltrano");
        listaProfissionais.add("Cicrano");
        return listaProfissionais;
    }

    private List<String> obterListaDatas() {
        List<String> listaDatas = new ArrayList<>();
        listaDatas.add("11/07/2023");
        listaDatas.add("12/07/2023");
        listaDatas.add("13/07/2023");
        return listaDatas;
    }
    private List<String> obterListaHorarios() {
        List<String> listaHorarios = new ArrayList<>();
        listaHorarios.add("08:00");
        listaHorarios.add("09:00");
        listaHorarios.add("10:00");
        return listaHorarios;
    }
}