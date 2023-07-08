package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class tela_Cadastro_profissional extends AppCompatActivity {

    private ListView listViewProfissionais;
    private List<Usuario> profissionais;

    private UsuarioAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_profissional);

        // Obter a lista de profissionais
        profissionais = MainActivity.getProfissionais();

        // Configurar o ListView
        listViewProfissionais = findViewById(R.id.listViewProfissionais);
        adapter = new UsuarioAdapter(this, profissionais);
        listViewProfissionais.setAdapter(adapter);


        // Configurar o clique em um item da lista
        listViewProfissionais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Abrir a activity de edição do profissional
                Intent intent = new Intent(tela_Cadastro_profissional.this, tela_Cadastro_profissional_editar.class);
                intent.putExtra("numeroProfissional",position);
                startActivity(intent);
            }
        });

        // Configurar o botão "Novo"
        Button novoButton = findViewById(R.id.btnNovo);
        novoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir a activity de cadastro de novo profissional
                Intent intent = new Intent(tela_Cadastro_profissional.this, tela_Cadastro_profissional_novo.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        // Atualiza a lista de profissionais (se necessário)
        profissionais = MainActivity.getProfissionais();

        // Notifique o adaptador de mudanças nos dados
        adapter.notifyDataSetChanged();
    }
}