package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Tela_Cadastro_profissional_editar extends AppCompatActivity {

    private EditText txtNome;
    private EditText txtTelefone;
    private EditText txtEmail;
    private EditText txtTipoUsuario;
    private EditText txtSenha;
    private EditText txtConfirmaSenha;

    private Button btnConcluir;

    private Button btnExcluir;

    private Usuario profissional;
    private int IDusuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_profissional_editar);

        // Obter o objeto Usuario passado como extra
        Intent intent = getIntent();
        if (intent != null) {
            IDusuario = intent.getIntExtra("IDusuario", -1);
            if (IDusuario!=-1) {
                profissional = MainActivity.bancoDeDados.getUsuarioByID(IDusuario);
            }
        }


        // Configurar os campos de texto
        txtNome = findViewById(R.id.txtNome);
        txtTelefone = findViewById(R.id.txtTelefone);
        txtEmail = findViewById(R.id.txtEmail);
        txtTipoUsuario = findViewById((R.id.txtTipoUsuario));
        txtSenha = findViewById(R.id.txtSenha);
        txtConfirmaSenha = findViewById(R.id.txtConfirmaSenha);
        btnConcluir = findViewById((R.id.btnConcluir));
        btnExcluir = findViewById((R.id.btnExcluir));

        // Preencher os campos com base nas informações do profissional
        if (profissional != null) {
            txtNome.setText(profissional.getNome());
            txtTelefone.setText(profissional.getTelefone());
            txtEmail.setText(String.valueOf(profissional.getEmail()));
            txtTipoUsuario.setText(String.valueOf(profissional.getTipoUsuario()));
            txtSenha.setText(String.valueOf(profissional.getSenha()));
            txtConfirmaSenha.setText(String.valueOf(profissional.getSenha()));
        }

        // Configurar o clique do botão "Concluir"
        btnConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obter os valores dos campos
                profissional.setNome(txtNome.getText().toString());
                profissional.setTelefone(txtTelefone.getText().toString());
                profissional.setEmail(txtEmail.getText().toString());
                profissional.setTipoUsuario(txtTipoUsuario.getText().toString());
                profissional.setSenha(txtSenha.getText().toString());
                MainActivity.bancoDeDados.atualizarUsuario(profissional);
                Toast.makeText(Tela_Cadastro_profissional_editar.this, "Profissional alterado com sucesso!", Toast.LENGTH_SHORT).show();

                // Encerrar a tela de edição e voltar para a MainActivity
                finish();

            }
        });

        // Configurar o clique do botão "Excluir Profissional"
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obter os valores dos campos
                MainActivity.bancoDeDados.removerUsuarioByID(IDusuario);

                Toast.makeText(Tela_Cadastro_profissional_editar.this, "Profissional excluído com sucesso!", Toast.LENGTH_SHORT).show();

                // Encerrar a tela de edição
                finish();

            }
        });


    }
}