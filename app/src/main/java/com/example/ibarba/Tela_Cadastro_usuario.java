package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class Tela_Cadastro_usuario extends AppCompatActivity {

    private ListView listViewUsuarios;
    private List<Usuario> usuarios;

    private UsuarioAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        // Configurar o ListView
        listViewUsuarios = findViewById(R.id.listViewUsuarios);
//        adapter = new UsuarioAdapter(this, profissionais);
//        listViewProfissionais.setAdapter(adapter);


        // Configurar o clique em um item da lista
        listViewUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                // Abrir a activity de edição do profissional
                Intent intent = new Intent(Tela_Cadastro_usuario.this, Tela_Cadastro_usuario_editar.class);
                intent.putExtra("IDusuario",usuarios.get(i).getIDusuario());
                startActivity(intent);
            }
        });

        // Configurar o botão "Novo"
        Button novoButton = findViewById(R.id.btnNovo);
        novoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir a activity de cadastro de novo usuário
                Intent intent = new Intent(Tela_Cadastro_usuario.this, Tela_Cadastro_usuario_novo.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        // Atualiza a lista de profissionais (se necessário)
        usuarios = MainActivity.bancoDeDados.getListaUsuarios();
        adapter = new UsuarioAdapter(this, usuarios);
        listViewUsuarios.setAdapter(adapter);

        // Notifique o adaptador de mudanças nos dados
        adapter.notifyDataSetChanged();
    }
}