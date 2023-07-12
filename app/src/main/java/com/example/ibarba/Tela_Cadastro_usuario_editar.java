package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Tela_Cadastro_usuario_editar extends AppCompatActivity {

    private EditText txtNome;
    private EditText txtTelefone;
    private EditText txtEmail;
    private EditText txtTipoUsuario;
    private EditText txtSenha;
    private EditText txtConfirmaSenha;

    private Button btnConcluir;

    private Button btnExcluir;

    private Usuario usuario;
    private int IDusuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario_editar);

        // Obter o objeto Usuario passado como extra
        Intent intent = getIntent();
        if (intent != null) {
            IDusuario = intent.getIntExtra("IDusuario", -1);
            if (IDusuario!=-1) {
                usuario = MainActivity.bancoDeDados.getUsuarioByID(IDusuario);
            }
        }


        // Configura os campos de texto
        txtNome = findViewById(R.id.txtNome);
        txtTelefone = findViewById(R.id.txtTelefone);
        txtEmail = findViewById(R.id.txtEmail);
        txtTipoUsuario = findViewById((R.id.txtTipoUsuario));
        txtSenha = findViewById(R.id.txtSenha);
        txtConfirmaSenha = findViewById(R.id.txtConfirmaSenha);
        btnConcluir = findViewById((R.id.btnConcluir));
        btnExcluir = findViewById((R.id.btnExcluir));

        // Preenche os campos com base nas informações do usuário
        if (usuario != null) {
            txtNome.setText(usuario.getNome());
            txtTelefone.setText(usuario.getTelefone());
            txtEmail.setText(String.valueOf(usuario.getEmail()));
            txtTipoUsuario.setText(String.valueOf(usuario.getTipoUsuario()));
            txtSenha.setText(String.valueOf(usuario.getSenha()));
            txtConfirmaSenha.setText(String.valueOf(usuario.getSenha()));
        }

        // Configura o clique do botão "Concluir"
        btnConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obter os valores dos campos
                usuario.setNome(txtNome.getText().toString());
                usuario.setTelefone(txtTelefone.getText().toString());
                usuario.setEmail(txtEmail.getText().toString());
                usuario.setTipoUsuario(txtTipoUsuario.getText().toString());
                usuario.setSenha(txtSenha.getText().toString());
                MainActivity.bancoDeDados.atualizarUsuario(usuario);
                Toast.makeText(Tela_Cadastro_usuario_editar.this, "Usuário alterado com sucesso!", Toast.LENGTH_SHORT).show();

                // Encerrar a tela de edição
                finish();

            }
        });

        // Configurar o clique do botão "Excluir Profissional"
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obter os valores dos campos
                MainActivity.bancoDeDados.removerUsuarioByID(IDusuario);

                Toast.makeText(Tela_Cadastro_usuario_editar.this, "Usuario excluído com sucesso!", Toast.LENGTH_SHORT).show();

                // Encerrar a tela de edição
                finish();

            }
        });


    }
}