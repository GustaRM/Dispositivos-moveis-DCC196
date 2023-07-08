package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class tela_Cadastro_cliente extends AppCompatActivity {

    private EditText txtNome;
    private EditText txtTelefone;
    private EditText txtEmail;
    private EditText txtSenha;
    private EditText txtConfirmaSenha;
    private Button btnConcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);


        // Inicializar os componentes
        txtNome = findViewById(R.id.txtNome);
        txtTelefone = findViewById(R.id.txtTelefone);
        txtEmail = findViewById(R.id.txtEmail);
        txtSenha = findViewById(R.id.txtSenha);
        txtConfirmaSenha = findViewById(R.id.txtConfirmaSenha);
        btnConcluir = findViewById(R.id.btnConcluir);

        // Configurar o clique do botão "Concluir"
        btnConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obter os valores dos campos
                String nome = txtNome.getText().toString();
                String telefone = txtTelefone.getText().toString();
                String email = txtEmail.getText().toString();
                String senha = txtSenha.getText().toString();
                String confirmaSenha = txtConfirmaSenha.getText().toString();

                // Verificar se a senha e a confirmação são iguais
                if (senha.equals(confirmaSenha)) {
                    // Criar um novo usuário com os dados fornecidos
                    Usuario novoUsuario = new Usuario(nome, telefone, email, "cliente", senha);

                    // Adicionar o novo usuário à lista existente na MainActivity
                    MainActivity.adicionarUsuario(novoUsuario);

                    // Exibir mensagem de sucesso
                    Toast.makeText(tela_Cadastro_cliente.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

                    // Encerrar a tela de cadastro e voltar para a MainActivity
                    finish();
                } else {
                    // Senha e confirmação não correspondem, exibir mensagem de erro
                    Toast.makeText(tela_Cadastro_cliente.this, "A senha e a confirmação não correspondem.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}