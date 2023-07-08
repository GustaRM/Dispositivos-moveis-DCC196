package com.example.ibarba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class tela_Cadastro_servico_editar extends AppCompatActivity {

    private EditText txtNome;
    private EditText txtDescricao;
    private EditText txtCusto;
    private EditText txtPrecoFinal;
    private EditText txtDuracao;

    private Button btnConcluir;

    private Button btnExcluir;

    private Servico servico;
    private int numeroServico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_servico_editar);

        // Obter o objeto Servico passado como extra
        Intent intent = getIntent();
        if (intent != null) {
            numeroServico = intent.getIntExtra("numeroServico", -1);
            if (numeroServico!=-1) {
                servico = MainActivity.getServicos().get(numeroServico);
            }
        }


        // Configurar os campos de texto
        txtNome = findViewById(R.id.txtNome);
        txtDescricao = findViewById(R.id.txtDescricao);
        txtCusto = findViewById(R.id.txtCusto);
        txtPrecoFinal = findViewById(R.id.txtPrecoFinal);
        txtDuracao = findViewById(R.id.txtDuracao);
        btnConcluir = findViewById((R.id.btnConcluir));
        btnExcluir = findViewById((R.id.btnExcluir));

        txtPrecoFinal.setText(String.valueOf(numeroServico));
        // Preencher os campos com base nas informações do serviço
        if (servico != null) {
            txtNome.setText(servico.getNome());
            txtDescricao.setText(servico.getDescricao());
            txtCusto.setText(String.valueOf(servico.getCusto()));
            txtPrecoFinal.setText(String.valueOf(servico.getPrecoFinal()));
            txtDuracao.setText(String.valueOf(servico.getDuracao()));
        }

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

                servico.setNome(nome);
                servico.setDescricao(descricao);
                servico.setCusto(custo);
                servico.setPrecoFinal(precoFinal);
                servico.setDuracao(duracao);

                Toast.makeText(tela_Cadastro_servico_editar.this, "Serviço alterado com sucesso!", Toast.LENGTH_SHORT).show();

                // Encerrar a tela de edição e voltar para a MainActivity
                finish();

            }
        });

        // Configurar o clique do botão "Excluir Serviço"
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obter os valores dos campos

                MainActivity.getServicos().remove(numeroServico);

                Toast.makeText(tela_Cadastro_servico_editar.this, "Serviço excluído com sucesso!", Toast.LENGTH_SHORT).show();

                // Encerrar a tela de edição e voltar para a MainActivity
                finish();

            }
        });


    }
}