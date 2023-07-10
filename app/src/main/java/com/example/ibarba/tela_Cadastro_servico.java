package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class tela_Cadastro_servico extends AppCompatActivity {

    private ListView listViewServicos;
    private List<Servico> servicos;

    private ServicoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_servico);

        // Obter a lista de serviços da MainActivity
 //       servicos = MainActivity.bancoDeDados.getListaServicos();

        // Configurar o ListView
        listViewServicos = findViewById(R.id.listViewServicos);
  //      adapter = new ServicoAdapter(this, servicos);
  //      listViewServicos.setAdapter(adapter);

        // Configurar o clique em um item da lista
        listViewServicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Abrir a activity de edição do serviço
                Intent intent = new Intent(tela_Cadastro_servico.this, tela_Cadastro_servico_editar.class);
                intent.putExtra("IDservico", servicos.get(position).getIDservico());
                startActivity(intent);
            }
        });

        // Configurar o botão "Novo"
        Button novoButton = findViewById(R.id.btnNovo);
        novoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir a activity de cadastro de novo serviço
                Intent intent = new Intent(tela_Cadastro_servico.this, tela_Cadastro_servico_novo.class);
                startActivity(intent);
            }
        });

        //Voltar para o menu inicial (Gestor)
        Button btnVoltar = findViewById((R.id.btnSair));
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Atualiza a lista de serviços (se necessário)
        servicos = MainActivity.bancoDeDados.getListaServicos();

        adapter = new ServicoAdapter(this, servicos);
        listViewServicos.setAdapter(adapter);
        // Notifique o adaptador de mudanças nos dados
//        adapter.notifyDataSetChanged();
    }
}