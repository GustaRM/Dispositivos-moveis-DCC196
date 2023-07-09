package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class tela_Cadastro_servico_novo extends AppCompatActivity {


    private EditText txtNome;
    private EditText txtDescricao;
    private EditText txtCusto;
    private EditText txtPrecoFinal;
    private EditText txtDuracao;
    private Button btnConcluir;

    private Servico servico;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_servico_novo);


        // Inicializar os componentes
        txtNome = findViewById(R.id.txtNome);
        txtDescricao = findViewById(R.id.txtDescricao);
        txtCusto = findViewById(R.id.txtCusto);
        txtPrecoFinal = findViewById(R.id.txtPrecoSugerido);
        txtDuracao = findViewById(R.id.txtDuracao);
        btnConcluir = findViewById(R.id.btnConcluir);

        // Configurar o clique do botão "Concluir"
        btnConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obter os valores dos campos
                String nome = txtNome.getText().toString();
                String descricao = txtDescricao.getText().toString();
                double custo = Double.parseDouble(txtCusto.getText().toString());
                double precoFinal = Double.parseDouble(txtPrecoFinal.getText().toString());
                int duracao = Integer.parseInt(txtDuracao.getText().toString());

                // Criar um novo serviço com os dados fornecidos
                Servico novoServico = new Servico(0, nome, descricao, custo, precoFinal, duracao);

                // Adicionar o novo serviço à lista existente na MainActivity
                MainActivity.bancoDeDados.adicionaServico(novoServico);

                // Exibir mensagem de sucesso
                Toast.makeText(tela_Cadastro_servico_novo.this, "Servico cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

                // Encerrar a tela de cadastro
                finish();

            }
        });
    }
}