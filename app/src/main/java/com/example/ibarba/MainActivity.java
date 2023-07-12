package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public static BancoDeDados bancoDeDados;
    private SQLiteDatabase db;

    public static int IDusuario=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Instancia o banco de dados
        bancoDeDados = new BancoDeDados(this);
        db = bancoDeDados.getWritableDatabase();
   //     bancoDeDados.apagaTabelas(db);
    //    bancoDeDados.onCreate(db);


        if (bancoDeDados.isTabelaVazia("Usuarios")) {
            bancoDeDados.popularUsuarios();
        }

        if (bancoDeDados.isTabelaVazia("Servicos")) {
            bancoDeDados.popularServicos();
        }
        if (bancoDeDados.isTabelaVazia("Atendimentos")) {
            bancoDeDados.populaTabelaAtendimentos();
        }


        EditText txtEmail = findViewById(R.id.txtEmail);
        EditText txtSenha = findViewById(R.id.txtSenha);
        Button btnEntrar = findViewById(R.id.btnEntrar);
        TextView txtCadastro = findViewById(R.id.txtCadastro);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                String senha = txtSenha.getText().toString();

                // Verifique se o email e a senha fornecidos estão na lista de usuários

                Usuario usuario = bancoDeDados.getUsuarioByEmailSenha(email, senha);

                if (usuario != null) {
                    IDusuario = usuario.getIDusuario();
                    // Usuário válido, redirecione para a tela apropriada com base no tipo de usuário
                    if (usuario.getTipoUsuario().equals("cliente")) {
                        // Redirecionar para a tela "Bem vindo cliente"
                        Intent intent = new Intent(MainActivity.this, tela_Menu_cliente.class);
                        startActivity(intent);
                    } else if (usuario.getTipoUsuario().equals("profissional")) {
                        // Redirecionar para a tela "Bem vindo profissional"
                        Intent intent = new Intent(MainActivity.this, tela_Menu_profissional.class);
                        startActivity(intent);
                    } else if (usuario.getTipoUsuario().equals("gestor")) {
                        // Redirecionar para a tela "Bem vindo gestor"
                        Intent intent = new Intent(MainActivity.this, tela_Menu_gestor.class);
                        startActivity(intent);
                    }
                } else {
                    // Usuário inválido, exiba a mensagem "Usuário inexistente"
                    Toast.makeText(MainActivity.this, "Usuário inexistente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Configurar um clique no texto de "Cadastre-se"

        txtCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirecionar para a tela de cadastro
                Intent intent = new Intent(MainActivity.this, tela_Autocadastro_cliente.class);
                startActivity(intent);
            }
        });

    }

    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}