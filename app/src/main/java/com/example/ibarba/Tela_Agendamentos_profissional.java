package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Tela_Agendamentos_profissional extends AppCompatActivity {

    private ArrayList<String> listaAgendamentosProfissional= new ArrayList<>();;
    private ArrayList<Integer> listaIDAtendimento = new ArrayList<>();

    private ArrayList<Double> listaPrecoFinal = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamentos_profissional);

        ListView lista = (ListView) findViewById(R.id.listaAgendamentos);
        ArrayList<String> agendamentos = preencherAgendamentos();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, agendamentos);
        lista.setAdapter(arrayAdapter);

        //Voltar para o menu anterior
        Button btnVoltar = findViewById((R.id.btnSair));
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Toast.makeText(getApplicationContext(), "Agendamentos: "+agendamentos.get(i).toString(), Toast.LENGTH_SHORT).show();
                // Abrir a activity para conclusão do agendamento
                Intent intent = new Intent(Tela_Agendamentos_profissional.this, Tela_Atendimento_profissional.class);
                intent.putExtra("IDAtendimento",listaIDAtendimento.get(i).intValue());
                intent.putExtra("precoFinal",listaPrecoFinal.get(i).doubleValue());
                intent.putExtra("descricaoAtendimento",listaAgendamentosProfissional.get(i));
                startActivity(intent);
            }
        });

    }

    protected void onResume() {
        super.onResume();
        ListView lista = (ListView) findViewById(R.id.listaAgendamentos);
        ArrayList<String> agendamentos = preencherAgendamentos();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, agendamentos);
        lista.setAdapter(arrayAdapter);
    }
    private ArrayList<String> preencherAgendamentos() {
        ArrayList<String> listaTemp = MainActivity.bancoDeDados.getListaAgendamentosProfissionalByIDusuario(MainActivity.IDusuario);

        listaIDAtendimento.clear();
        listaPrecoFinal.clear();
        listaAgendamentosProfissional.clear();

        for (String elemento : listaTemp) {
            // Divide o elemento em partes separadas por "::"
            String[] partes = elemento.split("::");
            int idAtendimento = Integer.parseInt(partes[0].trim());
            listaIDAtendimento.add(idAtendimento);
            Double precoFinal = Double.parseDouble(partes[1].trim());
            listaPrecoFinal.add(precoFinal);
            //Atribui a segunda parte à listaAgendamentosProfissional
            listaAgendamentosProfissional.add(partes[2].trim());

        }

        return listaAgendamentosProfissional;
    }
}