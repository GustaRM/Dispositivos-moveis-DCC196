package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class tela_Agendar_cliente extends AppCompatActivity {

    private Spinner spinnerServico;
    private Spinner spinnerProfissional;
    private Spinner spinnerData;
    private Spinner spinnerHorario;

    private Button btnConcluir;

    private List<Servico> listaServicos;
    private List<Usuario> listaProfissionais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar_cliente);

        spinnerServico = findViewById(R.id.spinnerServico);
        spinnerProfissional = findViewById(R.id.spinnerProfissional);
        spinnerData = findViewById(R.id.spinnerData);
        spinnerHorario = findViewById(R.id.spinnerHorario);
        btnConcluir = findViewById(R.id.btnConcluir);

        configuraServicos();
        configuraProfissionais();

        spinnerServico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

         //       Toast.makeText(tela_AgendarServico.this,parent.getSelectedItem().toString(),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(tela_Agendar_cliente.this,"Nenhum selecionado",Toast.LENGTH_LONG).show();
            }

        });

        spinnerProfissional.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            //    Toast.makeText(tela_AgendarServico.this,parent.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
                configuraDatas();
                configuraHorarios();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(tela_Agendar_cliente.this,"Nenhum selecionado",Toast.LENGTH_LONG).show();
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
                Toast.makeText(tela_Agendar_cliente.this,"Nenhum selecionado",Toast.LENGTH_LONG).show();
            }

        });
        spinnerHorario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

       //         Toast.makeText(tela_AgendarServico.this,parent.getSelectedItem().toString(),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(tela_Agendar_cliente.this,"Nenhum selecionado",Toast.LENGTH_LONG).show();
            }

        });

        // Configurar o clique do botão "Concluir"
        btnConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinnerData.getAdapter().getCount() > 0 && spinnerHorario.getAdapter().getCount() >0 ) {
                    salvaAgendamento();
                    Toast.makeText(tela_Agendar_cliente.this,"Agendamento salvo com sucesso.",Toast.LENGTH_LONG).show();
                    finish();
                }else {
                    Toast.makeText(tela_Agendar_cliente.this,"Escolha a data e o horário.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void salvaAgendamento() {
        String data = spinnerData.getSelectedItem().toString();
        String horaInicio = spinnerHorario.getSelectedItem().toString();;
        int IDServico = listaServicos.get(spinnerServico.getSelectedItemPosition()).getIDservico();
        int IDusuario_profissional = listaProfissionais.get(spinnerProfissional.getSelectedItemPosition()).getIDusuario();
        int IDusuario_cliente = MainActivity.IDusuario;
        double precoFinal = listaServicos.get(spinnerServico.getSelectedItemPosition()).getPrecoSugerido();
        String status = "agendado";

        Atendimento atendimento = new Atendimento(0, data, horaInicio, IDServico, IDusuario_profissional, IDusuario_cliente, precoFinal, status);
        MainActivity.bancoDeDados.adicionaAtendimento(atendimento);

        MainActivity.bancoDeDados.atualizarStatusAgendaProfissional(IDusuario_profissional, data, horaInicio, "Reservado cliente");

    }

    private void configuraServicos() {
        listaServicos = MainActivity.bancoDeDados.getListaServicos();
        List<String> listaServicos_spinner = new ArrayList<>();
        for (Servico servico : listaServicos) {
            listaServicos_spinner.add(servico.getNome());
        }

        ArrayAdapter<String> servicoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaServicos_spinner);
        spinnerServico.setAdapter(servicoAdapter);
    }

    private void configuraProfissionais() {
        listaProfissionais = MainActivity.bancoDeDados.getListaProfissionais();
        List<String> listaProfissionais_spinner = new ArrayList<>();
        for (Usuario profissional : listaProfissionais) {
            listaProfissionais_spinner.add(profissional.getNome());

        }

        ArrayAdapter<String> usuarioAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaProfissionais_spinner);
        spinnerProfissional.setAdapter(usuarioAdapter);
    }

    private void configuraDatas() {

        int IDusuario_profissional = listaProfissionais.get(spinnerProfissional.getSelectedItemPosition()).getIDusuario();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, MainActivity.bancoDeDados.buscarDatasDisponiveisByIDusuario_profissional(IDusuario_profissional));
        spinnerData.setAdapter(dataAdapter);
    }

    private void configuraHorarios() {

        if (spinnerData.getAdapter().getCount() > 0) {
            int IDusuario_profissional = listaProfissionais.get(spinnerProfissional.getSelectedItemPosition()).getIDusuario();
            String data = spinnerData.getSelectedItem().toString();

            List<String> listaDatas = MainActivity.bancoDeDados.buscarHorariosDisponiveisByIDusuario_profissionalData(IDusuario_profissional, data);

            ArrayAdapter<String> horarioAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                    listaDatas);
            spinnerHorario.setAdapter(horarioAdapter);
        } else {
            // Cria um adaptador vazio
            ArrayAdapter<String> emptyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<String>());
            // Define o adaptador vazio para o Spinner
            spinnerHorario.setAdapter(emptyAdapter);
        }
    }
}