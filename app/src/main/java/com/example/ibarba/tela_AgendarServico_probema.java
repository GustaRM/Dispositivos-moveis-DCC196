package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class tela_AgendarServico_probema extends AppCompatActivity {
    private Spinner spinnerServico;
    private Spinner spinnerProfissional;
    private Spinner spinnerData;
    private Spinner spinnerHorario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar_servico_problema);

        spinnerServico = findViewById(R.id.spinnerServico);
        spinnerProfissional = findViewById(R.id.spinnerProfissional);
        spinnerData = findViewById(R.id.spinnerData);
        spinnerHorario = findViewById(R.id.spinnerHorario);


        setupSpinners();

        // Configurar o clique em um item do spinner
        spinnerServico.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Abrir a activity de edição do serviço
                //      Intent intent = new Intent(tela_Cadastro_servico.this, tela_Cadastro_servico_editar.class);
                //        intent.putExtra("IDservico", servicos.get(position).getIDservico());
                //       startActivity(intent);
            }
        });


    }

    private void setupSpinners() {
       // ArrayAdapter<String> servicoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, obterListaServicos());
       // spinnerServico.setAdapter(servicoAdapter);
        //spinnerServico.setOnItemSelectedListener(this);

        // Configurar os outros spinners da mesma maneira
        // spinnerProfissional.setOnItemSelectedListener(this);
        // spinnerData.setOnItemSelectedListener(this);
        // spinnerHorario.setOnItemSelectedListener(this);


    }

    private List<String> obterListaServicos() {
        List<String> listaServicos = new ArrayList<>();
        listaServicos.add("Serviço 1");
        listaServicos.add("Serviço 2");
        listaServicos.add("Serviço 3");
        // Adicione mais itens conforme necessário
        return listaServicos;
    }
}

