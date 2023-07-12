package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Tela_Cadastro_usuario_novo extends AppCompatActivity {

    private EditText txtNome;
    private EditText txtTelefone;
    private EditText txtEmail;
    private EditText txtTipoUsuario;
    private EditText txtSenha;
    private EditText txtConfirmaSenha;

    private Button btnConcluir;

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario_novo);

        // Configurar os campos de texto
        txtNome = findViewById(R.id.txtNome);
        txtTelefone = findViewById(R.id.txtTelefone);
        txtEmail = findViewById(R.id.txtEmail);
        txtTipoUsuario = findViewById((R.id.txtTipoUsuario));
        txtSenha = findViewById(R.id.txtSenha);
        txtConfirmaSenha = findViewById(R.id.txtConfirmaSenha);
        btnConcluir = findViewById((R.id.btnConcluir));


        // Configurar o clique do botão "Concluir"
        btnConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Criar um novo usuario com os dados fornecidos
                Usuario novoUsuario = new Usuario(1,
                        txtNome.getText().toString(),
                        txtTelefone.getText().toString(),
                        txtEmail.getText().toString(),
                        txtTipoUsuario.getText().toString(),
                        txtSenha.getText().toString());
                // Adicionar o novo usuario à lista existente na MainActivity
                MainActivity.bancoDeDados.adicionaUsuario(novoUsuario);

                Toast.makeText(Tela_Cadastro_usuario_novo.this, "Usuário criado com sucesso!", Toast.LENGTH_SHORT).show();

                // Encerrar a tela de edição e voltar para a MainActivity
                finish();

            }
        });
    }
}